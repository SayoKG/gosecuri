package gosecuri;

public class Main
{

	public static void main(String[] args) throws Exception
	{
		String directory = "src/main/resources/"; // args[0];
		String directoryIdCards = "src/main/resources/idcards/"; // args[1];
		String directoryStaffs = "src/main/resources/staffs/"; // args[2];

		// Récupération url
		if (args.length != 0)
		{
			directory = args[0];
			directoryIdCards = args[1];
			directoryStaffs = args[2];
		}

		GoSecuriRun goSecuri = new GoSecuriRun();
		goSecuri.run(directory, directoryIdCards, directoryStaffs);

	}

}
