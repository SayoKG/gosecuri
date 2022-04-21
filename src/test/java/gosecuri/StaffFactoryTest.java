package gosecuri;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import utils.Utils;

public class StaffFactoryTest
{

	/**
	 * Init du matériel avant chaque tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void initMaterial() throws Exception
	{
		String directory = "src/test/resources/"; // args[0];
		// init matériel factory
		StaffFactory.factory.buildsAll(new File(directory + "liste.txt"));
	}

	/**
	 * Test la construction correcte d'un membre du staff
	 * 
	 * @throws IOException
	 * @throws BadMaterialException
	 * @throws BadFileException
	 */
	@Test
	public void t001_buildStaff() throws Exception
	{
		// Préparation
		String directoryIdCards = "src/test/resources/idcards/"; // args[1];
		String directoryStaffs = "src/test/resources/staffs/"; // args[2];

		String userId = "cberthier";

		File file = new File(directoryStaffs + userId + Utils.FORMAT_TXT);
		File fileID = new File(directoryIdCards + userId + Utils.FORMAT_PNG);

		// Construction du membre du staff
		Staff staff = StaffFactory.build(file, fileID);

		assertEquals(userId, staff.getId());
		assertEquals("Berthier", staff.getFirstName());
		assertEquals("Corinne", staff.getName());
		assertEquals("Surveillance entrepôt", staff.getMission());
		assertEquals(fileID, staff.getCarte());
		assertEquals(3, staff.getMateriels().size());

	}

	/**
	 * Test la construction correcte d'un membre du staff
	 * 
	 * @throws IOException
	 * @throws BadMaterialException
	 */
	@Test
	public void t002_buildStaff() throws IOException, BadMaterialException
	{
		String directory = "src/main/resources/"; // args[0];
		String directoryIdCards = "src/main/resources/idcards/"; // args[1];
		String directoryStaffs = "src/main/resources/staffs/"; // args[2];

		List<String> lines = FileUtils.readLines(new File(directory + "staff.txt"), Utils.ENCODING);
		List<Staff> staffs = StaffFactory.builds(lines, directoryStaffs, directoryIdCards);

		assertEquals(8, staffs.size());

	}
}
