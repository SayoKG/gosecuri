package utils;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import gosecuri.Staff;
import gosecuri.StaffFactory;

public class UtilsTest
{
	/**
	 * Permet de test le tri d'une liste de Staff par leur ID.
	 * 
	 * @throws Exception
	 */
	@Test
	public void t001_sortStaff() throws Exception
	{
		// Préparation
		String directory = "src/main/resources/"; // args[0];
		String directoryIdCards = "src/main/resources/idcards/"; // args[1];
		String directoryStaffs = "src/main/resources/staffs/"; // args[2];

		// init matériel factory
		StaffFactory.factory.buildsAll(new File(directory + "liste.txt"));

		// On génère le staff
		List<String> lines = FileUtils.readLines(new File(directory + "staff.txt"), Utils.ENCODING);
		List<Staff> staffs = StaffFactory.builds(lines, directoryStaffs, directoryIdCards);

		// On tri les membres du staff
		Utils.sortStaff(staffs);

		// Test
		assertEquals(8, staffs.size());
		List<String> staffId = Arrays.asList("afoley", "cberthier", "jmacclane", "jrouletabille", "jwest", "mriggs",
				"sconnor", "sholmes");

		for (int i = 0; i < 8; i++)
		{
			assertEquals(staffId.get(i), staffs.get(i).getId());
		}

	}
}
