package gosecuri;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
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

	/**
	 * Constructeur
	 */
	private HtmlFactory()
	{
		// rien
	}

	public static String buildIndex(List<Staff> staffs) throws TemplateException, IOException
	{
		// Récupération du template
		Template template = ViewTemplate.getTemplate().getIndexTemplate();

		// Récupération des identifiants des agents
		List<String> results = new ArrayList<String>();
		for (Staff staff : staffs)
		{
			results.add(staff.getId());
		}
		// Traitement template
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("staffs", results);

		// écriture
		String fileName = "html/index.html";
		write(template, input, fileName);

		return null;
	}

	/**
	 * Construit une page html pour un membre du staff donnée
	 * 
	 * @param staff le membre à utiliser pour créer la page html
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String build(List<Staff> staffs) throws IOException, TemplateException
	{
		// Récupération du template
		Template template = ViewTemplate.getTemplate().getPageTemplate();

		// On crée une page pour chaque agent
		for (Staff staff : staffs)
		{
			Map<String, Object> input = new HashMap<String, Object>();
			input.put("staff", staff);

			// enregistrement de la carte d'identité si elle n'est pas générique
			if (!Utils.DEFAULT_AVATAR_NAME.equals(staff.getCarte().getName()))
			{

				File fileDest = new File("html/img/id/" + staff.getCarte().getName());

				FileUtils.copyFile(staff.getCarte(), fileDest);

				staff.setCarte(new File("img/id/" + staff.getCarte().getName()));
			}

			// écriture
			String fileName = "html/staffs/" + staff.getId() + Utils.FORMAT_HTML;
			write(template, input, fileName);
		}

		return null;
	}

	/**
	 * Ecrit en console et dans un fichier de l'html à partir d'un template
	 * 
	 * @param template utilisé
	 * @param input    utilisé
	 * @param fileName nom du fichier
	 * @throws TemplateException
	 * @throws IOException
	 */
	private static void write(Template template, Map<String, Object> input, String fileName)
			throws TemplateException, IOException
	{
		// Write output to the console
		Writer consoleWriter = new OutputStreamWriter(System.out);
		template.process(input, consoleWriter);

		// For the sake of example, also write output into a file:
		try (Writer fileWriter = new FileWriter(new File(fileName), Charset.forName(Utils.ENCODING)))
		{
			template.process(input, fileWriter);

		}
	}

}
