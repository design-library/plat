/**
 * 
 */
package com.soramame.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.tag.common.core.OutSupport;

public class OutputStrTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;

	private String value;
    
    private boolean escapeXml = true;
    
    
    @Override
    public int doStartTag() throws JspException {
    	
        try {
            JspWriter writer = pageContext.getOut();
            for (String str : value.replaceAll("\r\n", "\n").replaceAll("\r", "\n").split("\n")) {
                OutSupport.out(pageContext, escapeXml, str);
                writer.write("<br />");
            }
            
        } catch (IOException e) {
            throw new JspException(e.getMessage());
            
        }
        return SKIP_BODY;
        
    }
 
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
        
    }
 
    public void setValue(String value) {
        this.value = value;
        
    }
 
    public void setEscapeXml(boolean escapeXml) {
        this.escapeXml = escapeXml;
        
    }
    
}
