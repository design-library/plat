/**
 * Copyright 2015 Plat.
 * 
 * This file is part of Pj Platform.
 *
 *  Pj Platform is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Pj Platform is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Pj Platform.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.soramame.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.tag.common.core.OutSupport;

/**
 * SummaryTag
 * 
 * @author Plat.
 * @version 1.0
 */
public class SummaryStrTag extends TagSupport {
	
	
	private static final long serialVersionUID = 1L;

	private String value;

	private Integer length;
    
    private boolean escapeXml = true;
    
    
    @Override
    public int doStartTag() throws JspException {
    	
        try {
        	String summary;
        	int valueLength = value.length();
        	if (valueLength > length) {
        		summary = value.substring(0, length);
        	} else {
        		summary = value;
        	}
    		OutSupport.out(pageContext, escapeXml, summary);
            
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
    
    public void setLength(Integer length) {
        this.length = length;
        
    }
 
    public void setEscapeXml(boolean escapeXml) {
        this.escapeXml = escapeXml;
        
    }
    

}
