package com.github.liaojiacan.pdf;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.*;
import org.xhtmlrenderer.resource.XMLResource;
import org.xml.sax.InputSource;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author liaojiacan
 */
public class PDFRender {

    private URL resourcePath;

    public  void createPDF(String url, String pdf)
            throws IOException, DocumentException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(pdf);
            ITextRenderer renderer = new ITextRenderer();
            ResourceLoaderUserAgent callback = new ResourceLoaderUserAgent(renderer.getOutputDevice());
            callback.setSharedContext(renderer.getSharedContext());
            renderer.getSharedContext ().setUserAgentCallback(callback);
            addFont(renderer);
            final Document doc = XMLResource.load(new InputSource(url)).getDocument();
            renderer.setDocument(doc, url);
            renderer.layout();
            List pages = renderer.getRootBox().getLayer().getPages();
            if(pages.size()>1){
                renderer.setListener(new LastPageListener(pages.size()));
            }
            renderer.createPDF(os, true, 0);
            os.close();
            os = null;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    private void addFont(ITextRenderer renderer) throws DocumentException, IOException {
        File font = null;
        String url =getResourcePath().getPath() + "resources/font/MicrosoftYaHei.ttf";
        font = new File(url);
        if(font.exists()){
            renderer.getFontResolver().addFont(font.toPath().toString(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        }else {
            throw new FileNotFoundException(font.toPath().toString());
        }
    }

    /**
     * 将HTML 生成PDF 并写到对应的输出流
     * @param os
     * @param html
     * @throws IOException
     * @throws DocumentException
     */
    public void htmlToPDF(OutputStream os ,String html)
            throws IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        ResourceLoaderUserAgent callback = new ResourceLoaderUserAgent(renderer.getOutputDevice());
        callback.setSharedContext(renderer.getSharedContext());
        renderer.getSharedContext ().setUserAgentCallback(callback);
        addFont(renderer);
        renderer.setDocumentFromString(html);
        renderer.layout();
        List pages = renderer.getRootBox().getLayer().getPages();
        if(pages.size()>1){
            renderer.setListener(new LastPageListener(pages.size()));
        }
       renderer.createPDF(os);
    }

    public void setResourcePath(URL resourcePath) {
        this.resourcePath = resourcePath;
    }

    public URL getResourcePath() {
        return resourcePath;
    }

    private  class ResourceLoaderUserAgent extends ITextUserAgent
    {
        public ResourceLoaderUserAgent(ITextOutputDevice outputDevice) {
            super(outputDevice);
        }

        protected InputStream resolveAndOpenStream(String url) {
            int index =0;
            try {
                if (url.indexOf("://") == -1) {
                     // maybe it's a file
                     File f = new File(url);
                    if (f.exists()) {
                        try {
                            url = f.toURI().toURL().toString();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }else if((index=url.indexOf("resources"))!=-1){
                    url = getResourcePath().getPath()+url.substring(index,url.length());
                    return resolveAndOpenStream(url);
                }

             } catch (Exception e) {
                e.printStackTrace();
            }
            InputStream is = super.resolveAndOpenStream(url);
            System.out.println("IN resolveAndOpenStream() " + url);
            return is;
        }
    }

    /**
     * 对最后一页加上 footer
     */
    private class LastPageListener extends DefaultPDFCreationListener{
        private int lastPageNo;

        public LastPageListener() {
        }
        public LastPageListener(int lastPageNo) {
            this.lastPageNo = lastPageNo;
        }

        @Override
        public void preWrite(ITextRenderer iTextRenderer, int pageCount) {

            iTextRenderer.getWriter().setPageEvent(new PdfPageEventHelper(){
                public void onEndPage(PdfWriter pdfWriter, com.lowagie.text.Document document) {
                    if(document.getPageNumber()==getLastPageNo()){
                        Image img = null;
                        try {
                            img = Image.getInstance(getResourcePath().getPath()+"resources/foot.png");
                            img.scaleToFit(PageSize.A4.getWidth(), img.getHeight());
                            img.setAbsolutePosition(0, 0);
                            pdfWriter.getDirectContent().addImage(img);
                        } catch (BadElementException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });

        }

        public int getLastPageNo() {
            return lastPageNo;
        }

        public void setLastPageNo(int lastPageNo) {
            this.lastPageNo = lastPageNo;
        }
    }


}
