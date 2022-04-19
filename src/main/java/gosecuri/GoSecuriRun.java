package gosecuri;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import utils.Utils;

public class GoSecuriRun
{
	/**
	 * D�clanche la cr�ation de page html � partir de fichiers fournis
	 * 
	 * @param directory        r�pertoire des infos g�n�ral
	 * @param directoryIdCards r�pertoire des cartes d'identit�s
	 * @param directoryStaffs  r�pertoire des fiches du staffs
	 * @throws Exception
	 */
	public void run(String directory, String directoryIdCards, String directoryStaffs) throws Exception
	{

		// init du mat�riel de la factory
		StaffFactory.factory.buildsAll(new File(directory + "liste.txt"));

		// On construit la liste des agents
		List<String> lines = FileUtils.readLines(new File(directory + "staff.txt"), Utils.ENCODING);
		List<Staff> staffs = StaffFactory.builds(lines, directoryStaffs, directoryIdCards);

		// On tri les agents par ordre alphab�tique
		staffs.sort(Comparator.comparing(Staff::getId));

		// On g�n�re les pages html
		HtmlFactory.buildIndex(staffs);
		HtmlFactory.build(staffs);
	}
}
