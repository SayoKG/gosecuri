package gosecuri;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import utils.Utils;

/**
 * Permet de construire du code html
 *
 */
public class HtmlFactory
{
	/** R�pertoire d'impression des fichiers html */
	private static String _directory = "html/";
	/** indicateur si on utilise les threads ou non */
	public static boolean thread = true;

	/**
	 * Constructeur
	 */
	private HtmlFactory()
	{
		// rien
	}

	public static void buildIndex(List<Staff> staffs) throws TemplateException, IOException
	{
		// R�cup�ration du template
		Template template = ViewTemplate.getTemplate().getIndexTemplate();

		// R�cup�ration des identifiants des agents
		List<String> results = new ArrayList<String>();
		for (Staff staff : staffs)
		{
			results.add(staff.getId());
		}
		// Traitement template
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("staffs", results);

		// �criture
		String fileName = _directory + "index.html";
		write(template, input, fileName);
	}

	/**
	 * Construit une page html pour un membre du staff donn�e
	 * 
	 * @param staff le membre � utiliser pour cr�er la page html
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void build(List<Staff> staffs) throws IOException, TemplateException
	{
		// R�cup�ration du template
		Template template = ViewTemplate.getTemplate().getPageTemplate();

		// init du r�pertoire staff si inexistant
		new File(_directory + "staffs").mkdir();

		// On cr�e une page pour chaque agent
		for (Staff staff : staffs)
		{
			Map<String, Object> input = new HashMap<String, Object>();
			input.put("staff", staff);

			// enregistrement de la carte d'identit� si elle n'est pas g�n�rique
			if (!Utils.DEFAULT_AVATAR_NAME.equals(staff.getCarte().getName()))
			{

				File fileDest = new File("html/img/id/" + staff.getCarte().getName());

				FileUtils.copyFile(staff.getCarte(), fileDest);

				staff.setCarte(new File("img/id/" + staff.getCarte().getName()));
			}

			// �criture
			String fileName = _directory + "staffs/" + staff.getId() + Utils.FORMAT_HTML;

			// L'�criture est r�alis� dans un autre thread
			if (thread)
			{
				ThreadGS th = new ThreadGS(template, input, fileName);
				th.start();
			} else
			{
				write(template, input, fileName);
			}

			// write(template, input, fileName);
		}
	}

	/**
	 * Ecrit en console et dans un fichier de l'html � partir d'un template
	 * 
	 * @param template utilis�
	 * @param input    utilis�
	 * @param fileName nom du fichier
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	public static synchronized void write(Template template, Map<String, Object> input, String fileName)
			throws TemplateException, IOException
	{
		// Write output to the console
		// Writer consoleWriter = new OutputStreamWriter(System.out);
		// template.process(input, consoleWriter);

		// For the sake of example, also write output into a file:
		try (Writer fileWriter = new FileWriter(new File(fileName), Charset.forName(Utils.ENCODING)))
		{
			template.process(input, fileWriter);

		}
	}

	public static void setDirectory(String directory)
	{
		_directory = directory;
	}

}
