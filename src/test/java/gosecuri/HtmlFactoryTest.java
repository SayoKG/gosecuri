package gosecuri;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import utils.Utils;

public class HtmlFactoryTest
{
	/**
	 * Construit la page d'index
	 */
	@Test
	public void t001_buildIndex() throws Exception
	{
		String directory = "src/main/resources/"; // args[0];
		String directoryIdCards = "src/main/resources/idcards/"; // args[1];
		String directoryStaffs = "src/main/resources/staffs/"; // args[2];

		// init du matériel de la factory
		StaffFactory.factory.buildsAll(new File(directory + Utils.LISTE_TXT));

		// On construit la liste des agents
		List<String> lines = FileUtils.readLines(new File(directory + Utils.STAFF_TXT), Utils.ENCODING);
		List<Staff> staffs = StaffFactory.builds(lines, directoryStaffs, directoryIdCards);

		// On tri les agents par ordre alphabétique de leur ID
		Utils.sortStaff(staffs);

		// On génère les pages html
		String directoryHtmlTest = "src/test/resources/fake/";
		HtmlFactory.setDirectory(directoryHtmlTest);
		HtmlFactory.buildIndex(staffs);

		File result = new File(directoryHtmlTest + "index.html");
		List<String> resultLines = FileUtils.readLines(result, Utils.ENCODING);
		File file = new File("src/test/resources/html/index.html");
		List<String> fileLines = FileUtils.readLines(file, Utils.ENCODING);

		assertEquals(fileLines.size(), resultLines.size());

		for (int i = 0; i < fileLines.size(); i++)
		{
			assertEquals(fileLines.get(i), resultLines.get(i));
		}

	}

	/**
	 * Construit la page d'un membre du staff
	 */
	@Test
	public void t002_buildStaff() throws Exception
	{
		String directory = "src/main/resources/"; // args[0];
		String directoryIdCards = "src/main/resources/idcards/"; // args[1];
		String directoryStaffs = "src/main/resources/staffs/"; // args[2];

		// init du matériel de la factory
		StaffFactory.factory.buildsAll(new File(directory + Utils.LISTE_TXT));

		// On construit la liste des agents
		List<String> lines = FileUtils.readLines(new File(directory + Utils.STAFF_TXT), Utils.ENCODING);
		List<Staff> staffs = StaffFactory.builds(lines, directoryStaffs, directoryIdCards);

		List<Staff> first = new ArrayList<Staff>();
		first.add(staffs.get(0));

		// On construit la fiche de staff
		String directoryHtmlTest = "src/test/resources/fake/";
		HtmlFactory.setDirectory(directoryHtmlTest);

		HtmlFactory.build(first);

		File result = new File(directoryHtmlTest + "staffs/cberthier.html");
		List<String> resultLines = FileUtils.readLines(result, Utils.ENCODING);
		File file = new File("src/test/resources/html/staffs/cberthier.html");
		List<String> fileLines = FileUtils.readLines(file, Utils.ENCODING);

		assertEquals(fileLines.size(), resultLines.size());

		for (int i = 0; i < fileLines.size(); i++)
		{
			assertEquals(fileLines.get(i), resultLines.get(i));
		}

	}
}
