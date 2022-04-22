package gosecuri;

import java.io.IOException;
import java.util.Map;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ThreadGS extends Thread
{
	/** Template a utiliser */
	Template template;
	/** objet en entré */
	Map<String, Object> input;
	/** Nom du fichier */
	String fileName;

	/**
	 * Constructeur
	 * 
	 * @param template
	 * @param input
	 * @param fileName
	 */
	public ThreadGS(Template template, Map<String, Object> input, String fileName)
	{
		this.fileName = fileName;
		this.input = input;
		this.template = template;
	}

	@Override
	public void run()
	{
		try
		{
			HtmlFactory.write(template, input, fileName);
		} catch (TemplateException e)
		{
			this.interrupt();
		} catch (IOException e)
		{
			this.interrupt();
		}

	}
}
