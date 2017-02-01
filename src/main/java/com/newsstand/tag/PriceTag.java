package com.newsstand.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * This class is a custom tag to represent price as float value.
 * Mainly it removes trailing zero after integer part.
 *
 * Example: input 3.0 (float value) -> output 3
 *          input 3.5 (float value) -> output 3.5
 */
public class PriceTag extends SimpleTagSupport {

	private Float price;

	public void setPrice(Float price) {
		this.price = price;
	}

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println(price.toString().replaceAll("\\.?0*$", "") + "₴");
	}
}
