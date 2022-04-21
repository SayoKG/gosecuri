package gosecuri;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

import utils.Utils;

public class GoSecuriRun
{
	/**
	 * Déclanche la création de page html à partir de fichiers fournis
	 * 
	 * @param directory        répertoire des infos général
	 * @param directoryIdCards répertoire des cartes d'identités
	 * @param directoryStaffs  répertoire des fiches du staffs
	 * @throws Exception
	 */
	public void run(String directory, String directoryIdCards, String directoryStaffs) throws Exception
	{
		System.out.println("---------------------INIT---------------------");
		// init du matériel de la factory
		StaffFactory.factory.buildsAll(new File(directory + Utils.LISTE_TXT));
		System.out.println("---MATERIEL : OK");

		// On construit la liste des agents
		List<String> lines = FileUtils.readLines(new File(directory + Utils.STAFF_TXT), Utils.ENCODING);
		List<Staff> staffs = StaffFactory.builds(lines, directoryStaffs, directoryIdCards);
		System.out.println("---STAFFS : OK");

		// On tri les agents par ordre alphabétique de leur ID
		Utils.sortStaff(staffs);

		// On génère les pages html
		HtmlFactory.buildIndex(staffs);
		System.out.println("---INDEX HTML : OK");
		HtmlFactory.build(staffs);
		System.out.println("---STAFF HTML : OK");
		System.out.println("---------------------END---------------------");
	}
}
