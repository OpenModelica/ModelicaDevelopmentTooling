
package org.modelica.mdt.ui.hover;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;

import org.modelica.mdt.ui.hover.HTML2TextReader;
import org.modelica.mdt.ui.hover.LineBreakingReader;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextPresentation;


public class HTMLTextPresenter implements DefaultInformationControl.IInformationPresenter, DefaultInformationControl.IInformationPresenterExtension {

	private static final String LINE_DELIM= System.getProperty("line.separator", "\n"); //$NON-NLS-1$ //$NON-NLS-2$

	private int fCounter;
	private boolean fEnforceUpperLineLimit;

	/**
	 * Enables using bold font in order not to clip the text.
	 * <p>
	 * <em>Enabling this is a hack.</em>
	 * 
	 * @since 3.2
	 */
	private boolean fUseBoldFont= false;

	public HTMLTextPresenter(boolean enforceUpperLineLimit) {
		super();
		fEnforceUpperLineLimit= enforceUpperLineLimit;
	}

	public HTMLTextPresenter() {
		this(true);
	}

	protected HTML2TextReader createReader(String hoverInfo, TextPresentation presentation) {
		return new HTML2TextReader(new StringReader(hoverInfo), presentation);
	}

	protected void adaptTextPresentation(TextPresentation presentation, int offset, int insertLength) {

		int yoursStart= offset;
		int yoursEnd=   offset + insertLength -1;
		yoursEnd= Math.max(yoursStart, yoursEnd);

		Iterator<?> e= presentation.getAllStyleRangeIterator();
		while (e.hasNext()) {

			StyleRange range= (StyleRange) e.next();

			int myStart= range.start;
			int myEnd=   range.start + range.length -1;
			myEnd= Math.max(myStart, myEnd);

			if (myEnd < yoursStart)
				continue;

			if (myStart < yoursStart)
				range.length += insertLength;
			else
				range.start += insertLength;
		}
	}

	private void append(StringBuffer buffer, String string, TextPresentation presentation) {

		int length= string.length();
		buffer.append(string);

		if (presentation != null)
			adaptTextPresentation(presentation, fCounter, length);

		fCounter += length;
	}

	private String getIndent(String line) {
		int length= line.length();

		int i= 0;
		while (i < length && Character.isWhitespace(line.charAt(i))) ++i;

		return (i == length ? line : line.substring(0, i)) + " "; //$NON-NLS-1$
	}

	/*
	 * @see IHoverInformationPresenter#updatePresentation(Display display, String, TextPresentation, int, int)
	 */
	public String updatePresentation(Display display, String hoverInfo, TextPresentation presentation, int maxWidth, int maxHeight) {
		return updatePresentation((Drawable)display, hoverInfo, presentation, maxWidth, maxHeight);
	}
	
	/*
	 * @see IHoverInformationPresenterExtension#updatePresentation(Drawable drawable, String, TextPresentation, int, int)
	 * @since 3.2
	 */
	public String updatePresentation(Drawable drawable, String hoverInfo, TextPresentation presentation, int maxWidth, int maxHeight) {

		if (hoverInfo == null)
			return null;

		GC gc= new GC(drawable);
		
		Font font= null;
		if (fUseBoldFont) {
			font= gc.getFont();
			FontData[] fontData= font.getFontData();
			for (int i= 0; i < fontData.length; i++)
				fontData[i].setStyle(SWT.BOLD);
			font= new Font(gc.getDevice(), fontData);
			gc.setFont(font);
		}
		
		try {

			StringBuffer buffer= new StringBuffer();
			int maxNumberOfLines= Math.round((float)maxHeight / gc.getFontMetrics().getHeight());

			fCounter= 0;
			LineBreakingReader reader= new LineBreakingReader(createReader(hoverInfo, presentation), gc, maxWidth);

			boolean lastLineFormatted= false;
			String lastLineIndent= null;

			String line=reader.readLine();
			boolean lineFormatted= reader.isFormattedLine();
			boolean firstLineProcessed= false;

			while (line != null) {

				if (fEnforceUpperLineLimit && maxNumberOfLines <= 0)
					break;

				if (firstLineProcessed) {
					if (!lastLineFormatted)
						append(buffer, LINE_DELIM, null);
					else {
						append(buffer, LINE_DELIM, presentation);
						if (lastLineIndent != null)
							append(buffer, lastLineIndent, presentation);
					}
				}

				append(buffer, line, null);
				firstLineProcessed= true;

				lastLineFormatted= lineFormatted;
				if (!lineFormatted)
					lastLineIndent= null;
				else if (lastLineIndent == null)
					lastLineIndent= getIndent(line);

				line= reader.readLine();
				lineFormatted= reader.isFormattedLine();

				maxNumberOfLines--;
			}

			if (line != null && buffer.length() > 0) {
				append(buffer, LINE_DELIM, lineFormatted ? presentation : null);
				append(buffer, "", presentation);
			}

			return trim(buffer, presentation);

		} catch (IOException e) {

			e.printStackTrace();
			return null;

		} finally {
			if (font != null)
				font.dispose();	
			gc.dispose();
		}
	}
	
	private String trim(StringBuffer buffer, TextPresentation presentation) {

		int length= buffer.length();

		int end= length -1;
		while (end >= 0 && Character.isWhitespace(buffer.charAt(end)))
			-- end;

		if (end == -1)
			return ""; //$NON-NLS-1$

		if (end < length -1)
			buffer.delete(end + 1, length);
		else
			end= length;

		int start= 0;
		while (start < end && Character.isWhitespace(buffer.charAt(start)))
			++ start;

		buffer.delete(0, start);
		presentation.setResultWindow(new Region(start, buffer.length()));
		return buffer.toString();
	}
}

