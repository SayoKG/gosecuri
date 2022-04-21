package utils;

import java.util.Comparator;
import java.util.List;

import gosecuri.Staff;

/**
 * Classe représentant des constantes
 *
 */
public class Utils
{
	/** Encodage par défaut */
	public final static String ENCODING = "UTF-8";
	/** Constante pour une inconnu */
	public final static String UNKNOWN = "inconnu";
	/** Constante pour l'image par défaut */
	public final static String DEFAULT_AVATAR_NAME = "default.png";
	/** Constante de format .txt */
	public final static String FORMAT_TXT = ".txt";
	/** Constante de format .png */
	public final static String FORMAT_PNG = ".png";
	/** Constante de format .html */
	public final static String FORMAT_HTML = ".html";
	/** Constante de nommage pour le fichier liste.txt */
	public final static String LISTE_TXT = "liste.txt";
	/** Constante de nommage pour le fichier staff.txt */
	public final static String STAFF_TXT = "staff.txt";

	/**
	 * Constructeur
	 */
	private Utils()
	{
		// Rien
	}

	/**
	 * Tri les agents par ordre alphabétique selon leur ID
	 * 
	 * @param staffs listes des membres du staff à trier
	 */
	public static void sortStaff(List<Staff> staffs)
	{
		staffs.sort(Comparator.comparing(Staff::getId));
	}

}
