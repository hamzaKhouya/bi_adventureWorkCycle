// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package awc.job_datawarehouse_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: JOB_datawarehouse Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class JOB_datawarehouse implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "JOB_datawarehouse";
	private final String projectName = "AWC";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					JOB_datawarehouse.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(JOB_datawarehouse.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUnite_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_AWC_JOB_datawarehouse = new byte[0];
		static byte[] commonByteArray_AWC_JOB_datawarehouse = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID;

		public int getID() {
			return this.ID;
		}

		public Integer Quantite_Commande;

		public Integer getQuantite_Commande() {
			return this.Quantite_Commande;
		}

		public Float Cout_standard;

		public Float getCout_standard() {
			return this.Cout_standard;
		}

		public String Nom_Client;

		public String getNom_Client() {
			return this.Nom_Client;
		}

		public String Ville;

		public String getVille() {
			return this.Ville;
		}

		public String Nom_Produit;

		public String getNom_Produit() {
			return this.Nom_Produit;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		public Float Prix;

		public Float getPrix() {
			return this.Prix;
		}

		public String Modele;

		public String getModele() {
			return this.Modele;
		}

		public String Sous_Categorie;

		public String getSous_Categorie() {
			return this.Sous_Categorie;
		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		public String Type_Business;

		public String getType_Business() {
			return this.Type_Business;
		}

		public String Type_Canal;

		public String getType_Canal() {
			return this.Type_Canal;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public String Continent;

		public String getContinent() {
			return this.Continent;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public String Etat_Civil;

		public String getEtat_Civil() {
			return this.Etat_Civil;
		}

		public String Genre;

		public String getGenre() {
			return this.Genre;
		}

		public Float Revenu_annuel;

		public Float getRevenu_annuel() {
			return this.Revenu_annuel;
		}

		public Integer Nbr_Enfants;

		public Integer getNbr_Enfants() {
			return this.Nbr_Enfants;
		}

		public String Poste;

		public String getPoste() {
			return this.Poste;
		}

		public String Propeietaire_Maison;

		public String getPropeietaire_Maison() {
			return this.Propeietaire_Maison;
		}

		public Integer Nbr_Voitures;

		public Integer getNbr_Voitures() {
			return this.Nbr_Voitures;
		}

		public String Annee;

		public String getAnnee() {
			return this.Annee;
		}

		public String Mois;

		public String getMois() {
			return this.Mois;
		}

		public String Semaine;

		public String getSemaine() {
			return this.Semaine;
		}

		public String Saison;

		public String getSaison() {
			return this.Saison;
		}

		public Integer Age;

		public Integer getAge() {
			return this.Age;
		}

		public String Tranche_d_age;

		public String getTranche_d_age() {
			return this.Tranche_d_age;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row5Struct other = (row5Struct) obj;

			if (this.ID != other.ID)
				return false;

			return true;
		}

		public void copyDataTo(row5Struct other) {

			other.ID = this.ID;
			other.Quantite_Commande = this.Quantite_Commande;
			other.Cout_standard = this.Cout_standard;
			other.Nom_Client = this.Nom_Client;
			other.Ville = this.Ville;
			other.Nom_Produit = this.Nom_Produit;
			other.Couleur = this.Couleur;
			other.Prix = this.Prix;
			other.Modele = this.Modele;
			other.Sous_Categorie = this.Sous_Categorie;
			other.Categorie = this.Categorie;
			other.Type_Business = this.Type_Business;
			other.Type_Canal = this.Type_Canal;
			other.Region = this.Region;
			other.Pays = this.Pays;
			other.Continent = this.Continent;
			other.Date = this.Date;
			other.Etat_Civil = this.Etat_Civil;
			other.Genre = this.Genre;
			other.Revenu_annuel = this.Revenu_annuel;
			other.Nbr_Enfants = this.Nbr_Enfants;
			other.Poste = this.Poste;
			other.Propeietaire_Maison = this.Propeietaire_Maison;
			other.Nbr_Voitures = this.Nbr_Voitures;
			other.Annee = this.Annee;
			other.Mois = this.Mois;
			other.Semaine = this.Semaine;
			other.Saison = this.Saison;
			other.Age = this.Age;
			other.Tranche_d_age = this.Tranche_d_age;

		}

		public void copyKeysDataTo(row5Struct other) {

			other.ID = this.ID;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_datawarehouse.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_datawarehouse.length == 0) {
						commonByteArray_AWC_JOB_datawarehouse = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_datawarehouse = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AWC_JOB_datawarehouse, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_datawarehouse, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_datawarehouse.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_datawarehouse.length == 0) {
						commonByteArray_AWC_JOB_datawarehouse = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_datawarehouse = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AWC_JOB_datawarehouse, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_datawarehouse, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_AWC_JOB_datawarehouse) {

				try {

					int length = 0;

					this.ID = dis.readInt();

					this.Quantite_Commande = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Cout_standard = null;
					} else {
						this.Cout_standard = dis.readFloat();
					}

					this.Nom_Client = readString(dis);

					this.Ville = readString(dis);

					this.Nom_Produit = readString(dis);

					this.Couleur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix = null;
					} else {
						this.Prix = dis.readFloat();
					}

					this.Modele = readString(dis);

					this.Sous_Categorie = readString(dis);

					this.Categorie = readString(dis);

					this.Type_Business = readString(dis);

					this.Type_Canal = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Continent = readString(dis);

					this.Date = readDate(dis);

					this.Etat_Civil = readString(dis);

					this.Genre = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Revenu_annuel = null;
					} else {
						this.Revenu_annuel = dis.readFloat();
					}

					this.Nbr_Enfants = readInteger(dis);

					this.Poste = readString(dis);

					this.Propeietaire_Maison = readString(dis);

					this.Nbr_Voitures = readInteger(dis);

					this.Annee = readString(dis);

					this.Mois = readString(dis);

					this.Semaine = readString(dis);

					this.Saison = readString(dis);

					this.Age = readInteger(dis);

					this.Tranche_d_age = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_AWC_JOB_datawarehouse) {

				try {

					int length = 0;

					this.ID = dis.readInt();

					this.Quantite_Commande = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Cout_standard = null;
					} else {
						this.Cout_standard = dis.readFloat();
					}

					this.Nom_Client = readString(dis);

					this.Ville = readString(dis);

					this.Nom_Produit = readString(dis);

					this.Couleur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix = null;
					} else {
						this.Prix = dis.readFloat();
					}

					this.Modele = readString(dis);

					this.Sous_Categorie = readString(dis);

					this.Categorie = readString(dis);

					this.Type_Business = readString(dis);

					this.Type_Canal = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Continent = readString(dis);

					this.Date = readDate(dis);

					this.Etat_Civil = readString(dis);

					this.Genre = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Revenu_annuel = null;
					} else {
						this.Revenu_annuel = dis.readFloat();
					}

					this.Nbr_Enfants = readInteger(dis);

					this.Poste = readString(dis);

					this.Propeietaire_Maison = readString(dis);

					this.Nbr_Voitures = readInteger(dis);

					this.Annee = readString(dis);

					this.Mois = readString(dis);

					this.Semaine = readString(dis);

					this.Saison = readString(dis);

					this.Age = readInteger(dis);

					this.Tranche_d_age = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID);

				// Integer

				writeInteger(this.Quantite_Commande, dos);

				// Float

				if (this.Cout_standard == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Cout_standard);
				}

				// String

				writeString(this.Nom_Client, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Couleur, dos);

				// Float

				if (this.Prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix);
				}

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.Sous_Categorie, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Type_Business, dos);

				// String

				writeString(this.Type_Canal, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Continent, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.Etat_Civil, dos);

				// String

				writeString(this.Genre, dos);

				// Float

				if (this.Revenu_annuel == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Revenu_annuel);
				}

				// Integer

				writeInteger(this.Nbr_Enfants, dos);

				// String

				writeString(this.Poste, dos);

				// String

				writeString(this.Propeietaire_Maison, dos);

				// Integer

				writeInteger(this.Nbr_Voitures, dos);

				// String

				writeString(this.Annee, dos);

				// String

				writeString(this.Mois, dos);

				// String

				writeString(this.Semaine, dos);

				// String

				writeString(this.Saison, dos);

				// Integer

				writeInteger(this.Age, dos);

				// String

				writeString(this.Tranche_d_age, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID);

				// Integer

				writeInteger(this.Quantite_Commande, dos);

				// Float

				if (this.Cout_standard == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Cout_standard);
				}

				// String

				writeString(this.Nom_Client, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Couleur, dos);

				// Float

				if (this.Prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix);
				}

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.Sous_Categorie, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Type_Business, dos);

				// String

				writeString(this.Type_Canal, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Continent, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.Etat_Civil, dos);

				// String

				writeString(this.Genre, dos);

				// Float

				if (this.Revenu_annuel == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Revenu_annuel);
				}

				// Integer

				writeInteger(this.Nbr_Enfants, dos);

				// String

				writeString(this.Poste, dos);

				// String

				writeString(this.Propeietaire_Maison, dos);

				// Integer

				writeInteger(this.Nbr_Voitures, dos);

				// String

				writeString(this.Annee, dos);

				// String

				writeString(this.Mois, dos);

				// String

				writeString(this.Semaine, dos);

				// String

				writeString(this.Saison, dos);

				// Integer

				writeInteger(this.Age, dos);

				// String

				writeString(this.Tranche_d_age, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID=" + String.valueOf(ID));
			sb.append(",Quantite_Commande=" + String.valueOf(Quantite_Commande));
			sb.append(",Cout_standard=" + String.valueOf(Cout_standard));
			sb.append(",Nom_Client=" + Nom_Client);
			sb.append(",Ville=" + Ville);
			sb.append(",Nom_Produit=" + Nom_Produit);
			sb.append(",Couleur=" + Couleur);
			sb.append(",Prix=" + String.valueOf(Prix));
			sb.append(",Modele=" + Modele);
			sb.append(",Sous_Categorie=" + Sous_Categorie);
			sb.append(",Categorie=" + Categorie);
			sb.append(",Type_Business=" + Type_Business);
			sb.append(",Type_Canal=" + Type_Canal);
			sb.append(",Region=" + Region);
			sb.append(",Pays=" + Pays);
			sb.append(",Continent=" + Continent);
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",Etat_Civil=" + Etat_Civil);
			sb.append(",Genre=" + Genre);
			sb.append(",Revenu_annuel=" + String.valueOf(Revenu_annuel));
			sb.append(",Nbr_Enfants=" + String.valueOf(Nbr_Enfants));
			sb.append(",Poste=" + Poste);
			sb.append(",Propeietaire_Maison=" + Propeietaire_Maison);
			sb.append(",Nbr_Voitures=" + String.valueOf(Nbr_Voitures));
			sb.append(",Annee=" + Annee);
			sb.append(",Mois=" + Mois);
			sb.append(",Semaine=" + Semaine);
			sb.append(",Saison=" + Saison);
			sb.append(",Age=" + String.valueOf(Age));
			sb.append(",Tranche_d_age=" + Tranche_d_age);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID, other.ID);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_AWC_JOB_datawarehouse = new byte[0];
		static byte[] commonByteArray_AWC_JOB_datawarehouse = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID;

		public int getID() {
			return this.ID;
		}

		public Integer Quantite_Commande;

		public Integer getQuantite_Commande() {
			return this.Quantite_Commande;
		}

		public Float Cout_standard;

		public Float getCout_standard() {
			return this.Cout_standard;
		}

		public String Nom_Client;

		public String getNom_Client() {
			return this.Nom_Client;
		}

		public String Ville;

		public String getVille() {
			return this.Ville;
		}

		public String Nom_Produit;

		public String getNom_Produit() {
			return this.Nom_Produit;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		public Float Prix;

		public Float getPrix() {
			return this.Prix;
		}

		public String Modele;

		public String getModele() {
			return this.Modele;
		}

		public String Sous_Categorie;

		public String getSous_Categorie() {
			return this.Sous_Categorie;
		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		public String Type_Business;

		public String getType_Business() {
			return this.Type_Business;
		}

		public String Type_Canal;

		public String getType_Canal() {
			return this.Type_Canal;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public String Continent;

		public String getContinent() {
			return this.Continent;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public String Etat_Civil;

		public String getEtat_Civil() {
			return this.Etat_Civil;
		}

		public String Genre;

		public String getGenre() {
			return this.Genre;
		}

		public Float Revenu_annuel;

		public Float getRevenu_annuel() {
			return this.Revenu_annuel;
		}

		public Integer Nbr_Enfants;

		public Integer getNbr_Enfants() {
			return this.Nbr_Enfants;
		}

		public String Poste;

		public String getPoste() {
			return this.Poste;
		}

		public String Propeietaire_Maison;

		public String getPropeietaire_Maison() {
			return this.Propeietaire_Maison;
		}

		public Integer Nbr_Voitures;

		public Integer getNbr_Voitures() {
			return this.Nbr_Voitures;
		}

		public String Annee;

		public String getAnnee() {
			return this.Annee;
		}

		public String Mois;

		public String getMois() {
			return this.Mois;
		}

		public String Semaine;

		public String getSemaine() {
			return this.Semaine;
		}

		public String Saison;

		public String getSaison() {
			return this.Saison;
		}

		public Integer Age;

		public Integer getAge() {
			return this.Age;
		}

		public String Tranche_d_age;

		public String getTranche_d_age() {
			return this.Tranche_d_age;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row1Struct other = (row1Struct) obj;

			if (this.ID != other.ID)
				return false;

			return true;
		}

		public void copyDataTo(row1Struct other) {

			other.ID = this.ID;
			other.Quantite_Commande = this.Quantite_Commande;
			other.Cout_standard = this.Cout_standard;
			other.Nom_Client = this.Nom_Client;
			other.Ville = this.Ville;
			other.Nom_Produit = this.Nom_Produit;
			other.Couleur = this.Couleur;
			other.Prix = this.Prix;
			other.Modele = this.Modele;
			other.Sous_Categorie = this.Sous_Categorie;
			other.Categorie = this.Categorie;
			other.Type_Business = this.Type_Business;
			other.Type_Canal = this.Type_Canal;
			other.Region = this.Region;
			other.Pays = this.Pays;
			other.Continent = this.Continent;
			other.Date = this.Date;
			other.Etat_Civil = this.Etat_Civil;
			other.Genre = this.Genre;
			other.Revenu_annuel = this.Revenu_annuel;
			other.Nbr_Enfants = this.Nbr_Enfants;
			other.Poste = this.Poste;
			other.Propeietaire_Maison = this.Propeietaire_Maison;
			other.Nbr_Voitures = this.Nbr_Voitures;
			other.Annee = this.Annee;
			other.Mois = this.Mois;
			other.Semaine = this.Semaine;
			other.Saison = this.Saison;
			other.Age = this.Age;
			other.Tranche_d_age = this.Tranche_d_age;

		}

		public void copyKeysDataTo(row1Struct other) {

			other.ID = this.ID;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_datawarehouse.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_datawarehouse.length == 0) {
						commonByteArray_AWC_JOB_datawarehouse = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_datawarehouse = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AWC_JOB_datawarehouse, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_datawarehouse, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_datawarehouse.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_datawarehouse.length == 0) {
						commonByteArray_AWC_JOB_datawarehouse = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_datawarehouse = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AWC_JOB_datawarehouse, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_datawarehouse, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_AWC_JOB_datawarehouse) {

				try {

					int length = 0;

					this.ID = dis.readInt();

					this.Quantite_Commande = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Cout_standard = null;
					} else {
						this.Cout_standard = dis.readFloat();
					}

					this.Nom_Client = readString(dis);

					this.Ville = readString(dis);

					this.Nom_Produit = readString(dis);

					this.Couleur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix = null;
					} else {
						this.Prix = dis.readFloat();
					}

					this.Modele = readString(dis);

					this.Sous_Categorie = readString(dis);

					this.Categorie = readString(dis);

					this.Type_Business = readString(dis);

					this.Type_Canal = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Continent = readString(dis);

					this.Date = readDate(dis);

					this.Etat_Civil = readString(dis);

					this.Genre = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Revenu_annuel = null;
					} else {
						this.Revenu_annuel = dis.readFloat();
					}

					this.Nbr_Enfants = readInteger(dis);

					this.Poste = readString(dis);

					this.Propeietaire_Maison = readString(dis);

					this.Nbr_Voitures = readInteger(dis);

					this.Annee = readString(dis);

					this.Mois = readString(dis);

					this.Semaine = readString(dis);

					this.Saison = readString(dis);

					this.Age = readInteger(dis);

					this.Tranche_d_age = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_AWC_JOB_datawarehouse) {

				try {

					int length = 0;

					this.ID = dis.readInt();

					this.Quantite_Commande = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Cout_standard = null;
					} else {
						this.Cout_standard = dis.readFloat();
					}

					this.Nom_Client = readString(dis);

					this.Ville = readString(dis);

					this.Nom_Produit = readString(dis);

					this.Couleur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix = null;
					} else {
						this.Prix = dis.readFloat();
					}

					this.Modele = readString(dis);

					this.Sous_Categorie = readString(dis);

					this.Categorie = readString(dis);

					this.Type_Business = readString(dis);

					this.Type_Canal = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Continent = readString(dis);

					this.Date = readDate(dis);

					this.Etat_Civil = readString(dis);

					this.Genre = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Revenu_annuel = null;
					} else {
						this.Revenu_annuel = dis.readFloat();
					}

					this.Nbr_Enfants = readInteger(dis);

					this.Poste = readString(dis);

					this.Propeietaire_Maison = readString(dis);

					this.Nbr_Voitures = readInteger(dis);

					this.Annee = readString(dis);

					this.Mois = readString(dis);

					this.Semaine = readString(dis);

					this.Saison = readString(dis);

					this.Age = readInteger(dis);

					this.Tranche_d_age = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID);

				// Integer

				writeInteger(this.Quantite_Commande, dos);

				// Float

				if (this.Cout_standard == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Cout_standard);
				}

				// String

				writeString(this.Nom_Client, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Couleur, dos);

				// Float

				if (this.Prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix);
				}

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.Sous_Categorie, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Type_Business, dos);

				// String

				writeString(this.Type_Canal, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Continent, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.Etat_Civil, dos);

				// String

				writeString(this.Genre, dos);

				// Float

				if (this.Revenu_annuel == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Revenu_annuel);
				}

				// Integer

				writeInteger(this.Nbr_Enfants, dos);

				// String

				writeString(this.Poste, dos);

				// String

				writeString(this.Propeietaire_Maison, dos);

				// Integer

				writeInteger(this.Nbr_Voitures, dos);

				// String

				writeString(this.Annee, dos);

				// String

				writeString(this.Mois, dos);

				// String

				writeString(this.Semaine, dos);

				// String

				writeString(this.Saison, dos);

				// Integer

				writeInteger(this.Age, dos);

				// String

				writeString(this.Tranche_d_age, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID);

				// Integer

				writeInteger(this.Quantite_Commande, dos);

				// Float

				if (this.Cout_standard == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Cout_standard);
				}

				// String

				writeString(this.Nom_Client, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Couleur, dos);

				// Float

				if (this.Prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix);
				}

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.Sous_Categorie, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Type_Business, dos);

				// String

				writeString(this.Type_Canal, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Continent, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.Etat_Civil, dos);

				// String

				writeString(this.Genre, dos);

				// Float

				if (this.Revenu_annuel == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Revenu_annuel);
				}

				// Integer

				writeInteger(this.Nbr_Enfants, dos);

				// String

				writeString(this.Poste, dos);

				// String

				writeString(this.Propeietaire_Maison, dos);

				// Integer

				writeInteger(this.Nbr_Voitures, dos);

				// String

				writeString(this.Annee, dos);

				// String

				writeString(this.Mois, dos);

				// String

				writeString(this.Semaine, dos);

				// String

				writeString(this.Saison, dos);

				// Integer

				writeInteger(this.Age, dos);

				// String

				writeString(this.Tranche_d_age, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID=" + String.valueOf(ID));
			sb.append(",Quantite_Commande=" + String.valueOf(Quantite_Commande));
			sb.append(",Cout_standard=" + String.valueOf(Cout_standard));
			sb.append(",Nom_Client=" + Nom_Client);
			sb.append(",Ville=" + Ville);
			sb.append(",Nom_Produit=" + Nom_Produit);
			sb.append(",Couleur=" + Couleur);
			sb.append(",Prix=" + String.valueOf(Prix));
			sb.append(",Modele=" + Modele);
			sb.append(",Sous_Categorie=" + Sous_Categorie);
			sb.append(",Categorie=" + Categorie);
			sb.append(",Type_Business=" + Type_Business);
			sb.append(",Type_Canal=" + Type_Canal);
			sb.append(",Region=" + Region);
			sb.append(",Pays=" + Pays);
			sb.append(",Continent=" + Continent);
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",Etat_Civil=" + Etat_Civil);
			sb.append(",Genre=" + Genre);
			sb.append(",Revenu_annuel=" + String.valueOf(Revenu_annuel));
			sb.append(",Nbr_Enfants=" + String.valueOf(Nbr_Enfants));
			sb.append(",Poste=" + Poste);
			sb.append(",Propeietaire_Maison=" + Propeietaire_Maison);
			sb.append(",Nbr_Voitures=" + String.valueOf(Nbr_Voitures));
			sb.append(",Annee=" + Annee);
			sb.append(",Mois=" + Mois);
			sb.append(",Semaine=" + Semaine);
			sb.append(",Saison=" + Saison);
			sb.append(",Age=" + String.valueOf(Age));
			sb.append(",Tranche_d_age=" + Tranche_d_age);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID, other.ID);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_AWC_JOB_datawarehouse = new byte[0];
		static byte[] commonByteArray_AWC_JOB_datawarehouse = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID;

		public int getID() {
			return this.ID;
		}

		public Integer Quantite_Commande;

		public Integer getQuantite_Commande() {
			return this.Quantite_Commande;
		}

		public Float Cout_standard;

		public Float getCout_standard() {
			return this.Cout_standard;
		}

		public String Nom_Client;

		public String getNom_Client() {
			return this.Nom_Client;
		}

		public String Ville;

		public String getVille() {
			return this.Ville;
		}

		public String Nom_Produit;

		public String getNom_Produit() {
			return this.Nom_Produit;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		public Float Prix;

		public Float getPrix() {
			return this.Prix;
		}

		public String Modele;

		public String getModele() {
			return this.Modele;
		}

		public String Sous_Categorie;

		public String getSous_Categorie() {
			return this.Sous_Categorie;
		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		public String Type_Business;

		public String getType_Business() {
			return this.Type_Business;
		}

		public String Type_Canal;

		public String getType_Canal() {
			return this.Type_Canal;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public String Continent;

		public String getContinent() {
			return this.Continent;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public String Etat_Civil;

		public String getEtat_Civil() {
			return this.Etat_Civil;
		}

		public String Genre;

		public String getGenre() {
			return this.Genre;
		}

		public Float Revenu_annuel;

		public Float getRevenu_annuel() {
			return this.Revenu_annuel;
		}

		public Integer Nbr_Enfants;

		public Integer getNbr_Enfants() {
			return this.Nbr_Enfants;
		}

		public String Poste;

		public String getPoste() {
			return this.Poste;
		}

		public String Propeietaire_Maison;

		public String getPropeietaire_Maison() {
			return this.Propeietaire_Maison;
		}

		public Integer Nbr_Voitures;

		public Integer getNbr_Voitures() {
			return this.Nbr_Voitures;
		}

		public String Annee;

		public String getAnnee() {
			return this.Annee;
		}

		public String Mois;

		public String getMois() {
			return this.Mois;
		}

		public String Semaine;

		public String getSemaine() {
			return this.Semaine;
		}

		public String Saison;

		public String getSaison() {
			return this.Saison;
		}

		public Integer Age;

		public Integer getAge() {
			return this.Age;
		}

		public String Tranche_d_age;

		public String getTranche_d_age() {
			return this.Tranche_d_age;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row2Struct other = (row2Struct) obj;

			if (this.ID != other.ID)
				return false;

			return true;
		}

		public void copyDataTo(row2Struct other) {

			other.ID = this.ID;
			other.Quantite_Commande = this.Quantite_Commande;
			other.Cout_standard = this.Cout_standard;
			other.Nom_Client = this.Nom_Client;
			other.Ville = this.Ville;
			other.Nom_Produit = this.Nom_Produit;
			other.Couleur = this.Couleur;
			other.Prix = this.Prix;
			other.Modele = this.Modele;
			other.Sous_Categorie = this.Sous_Categorie;
			other.Categorie = this.Categorie;
			other.Type_Business = this.Type_Business;
			other.Type_Canal = this.Type_Canal;
			other.Region = this.Region;
			other.Pays = this.Pays;
			other.Continent = this.Continent;
			other.Date = this.Date;
			other.Etat_Civil = this.Etat_Civil;
			other.Genre = this.Genre;
			other.Revenu_annuel = this.Revenu_annuel;
			other.Nbr_Enfants = this.Nbr_Enfants;
			other.Poste = this.Poste;
			other.Propeietaire_Maison = this.Propeietaire_Maison;
			other.Nbr_Voitures = this.Nbr_Voitures;
			other.Annee = this.Annee;
			other.Mois = this.Mois;
			other.Semaine = this.Semaine;
			other.Saison = this.Saison;
			other.Age = this.Age;
			other.Tranche_d_age = this.Tranche_d_age;

		}

		public void copyKeysDataTo(row2Struct other) {

			other.ID = this.ID;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_datawarehouse.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_datawarehouse.length == 0) {
						commonByteArray_AWC_JOB_datawarehouse = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_datawarehouse = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AWC_JOB_datawarehouse, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_datawarehouse, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_datawarehouse.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_datawarehouse.length == 0) {
						commonByteArray_AWC_JOB_datawarehouse = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_datawarehouse = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AWC_JOB_datawarehouse, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_datawarehouse, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_AWC_JOB_datawarehouse) {

				try {

					int length = 0;

					this.ID = dis.readInt();

					this.Quantite_Commande = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Cout_standard = null;
					} else {
						this.Cout_standard = dis.readFloat();
					}

					this.Nom_Client = readString(dis);

					this.Ville = readString(dis);

					this.Nom_Produit = readString(dis);

					this.Couleur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix = null;
					} else {
						this.Prix = dis.readFloat();
					}

					this.Modele = readString(dis);

					this.Sous_Categorie = readString(dis);

					this.Categorie = readString(dis);

					this.Type_Business = readString(dis);

					this.Type_Canal = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Continent = readString(dis);

					this.Date = readDate(dis);

					this.Etat_Civil = readString(dis);

					this.Genre = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Revenu_annuel = null;
					} else {
						this.Revenu_annuel = dis.readFloat();
					}

					this.Nbr_Enfants = readInteger(dis);

					this.Poste = readString(dis);

					this.Propeietaire_Maison = readString(dis);

					this.Nbr_Voitures = readInteger(dis);

					this.Annee = readString(dis);

					this.Mois = readString(dis);

					this.Semaine = readString(dis);

					this.Saison = readString(dis);

					this.Age = readInteger(dis);

					this.Tranche_d_age = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_AWC_JOB_datawarehouse) {

				try {

					int length = 0;

					this.ID = dis.readInt();

					this.Quantite_Commande = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Cout_standard = null;
					} else {
						this.Cout_standard = dis.readFloat();
					}

					this.Nom_Client = readString(dis);

					this.Ville = readString(dis);

					this.Nom_Produit = readString(dis);

					this.Couleur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix = null;
					} else {
						this.Prix = dis.readFloat();
					}

					this.Modele = readString(dis);

					this.Sous_Categorie = readString(dis);

					this.Categorie = readString(dis);

					this.Type_Business = readString(dis);

					this.Type_Canal = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Continent = readString(dis);

					this.Date = readDate(dis);

					this.Etat_Civil = readString(dis);

					this.Genre = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Revenu_annuel = null;
					} else {
						this.Revenu_annuel = dis.readFloat();
					}

					this.Nbr_Enfants = readInteger(dis);

					this.Poste = readString(dis);

					this.Propeietaire_Maison = readString(dis);

					this.Nbr_Voitures = readInteger(dis);

					this.Annee = readString(dis);

					this.Mois = readString(dis);

					this.Semaine = readString(dis);

					this.Saison = readString(dis);

					this.Age = readInteger(dis);

					this.Tranche_d_age = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID);

				// Integer

				writeInteger(this.Quantite_Commande, dos);

				// Float

				if (this.Cout_standard == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Cout_standard);
				}

				// String

				writeString(this.Nom_Client, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Couleur, dos);

				// Float

				if (this.Prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix);
				}

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.Sous_Categorie, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Type_Business, dos);

				// String

				writeString(this.Type_Canal, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Continent, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.Etat_Civil, dos);

				// String

				writeString(this.Genre, dos);

				// Float

				if (this.Revenu_annuel == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Revenu_annuel);
				}

				// Integer

				writeInteger(this.Nbr_Enfants, dos);

				// String

				writeString(this.Poste, dos);

				// String

				writeString(this.Propeietaire_Maison, dos);

				// Integer

				writeInteger(this.Nbr_Voitures, dos);

				// String

				writeString(this.Annee, dos);

				// String

				writeString(this.Mois, dos);

				// String

				writeString(this.Semaine, dos);

				// String

				writeString(this.Saison, dos);

				// Integer

				writeInteger(this.Age, dos);

				// String

				writeString(this.Tranche_d_age, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID);

				// Integer

				writeInteger(this.Quantite_Commande, dos);

				// Float

				if (this.Cout_standard == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Cout_standard);
				}

				// String

				writeString(this.Nom_Client, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Couleur, dos);

				// Float

				if (this.Prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix);
				}

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.Sous_Categorie, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Type_Business, dos);

				// String

				writeString(this.Type_Canal, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Continent, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.Etat_Civil, dos);

				// String

				writeString(this.Genre, dos);

				// Float

				if (this.Revenu_annuel == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Revenu_annuel);
				}

				// Integer

				writeInteger(this.Nbr_Enfants, dos);

				// String

				writeString(this.Poste, dos);

				// String

				writeString(this.Propeietaire_Maison, dos);

				// Integer

				writeInteger(this.Nbr_Voitures, dos);

				// String

				writeString(this.Annee, dos);

				// String

				writeString(this.Mois, dos);

				// String

				writeString(this.Semaine, dos);

				// String

				writeString(this.Saison, dos);

				// Integer

				writeInteger(this.Age, dos);

				// String

				writeString(this.Tranche_d_age, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID=" + String.valueOf(ID));
			sb.append(",Quantite_Commande=" + String.valueOf(Quantite_Commande));
			sb.append(",Cout_standard=" + String.valueOf(Cout_standard));
			sb.append(",Nom_Client=" + Nom_Client);
			sb.append(",Ville=" + Ville);
			sb.append(",Nom_Produit=" + Nom_Produit);
			sb.append(",Couleur=" + Couleur);
			sb.append(",Prix=" + String.valueOf(Prix));
			sb.append(",Modele=" + Modele);
			sb.append(",Sous_Categorie=" + Sous_Categorie);
			sb.append(",Categorie=" + Categorie);
			sb.append(",Type_Business=" + Type_Business);
			sb.append(",Type_Canal=" + Type_Canal);
			sb.append(",Region=" + Region);
			sb.append(",Pays=" + Pays);
			sb.append(",Continent=" + Continent);
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",Etat_Civil=" + Etat_Civil);
			sb.append(",Genre=" + Genre);
			sb.append(",Revenu_annuel=" + String.valueOf(Revenu_annuel));
			sb.append(",Nbr_Enfants=" + String.valueOf(Nbr_Enfants));
			sb.append(",Poste=" + Poste);
			sb.append(",Propeietaire_Maison=" + Propeietaire_Maison);
			sb.append(",Nbr_Voitures=" + String.valueOf(Nbr_Voitures));
			sb.append(",Annee=" + Annee);
			sb.append(",Mois=" + Mois);
			sb.append(",Semaine=" + Semaine);
			sb.append(",Saison=" + Saison);
			sb.append(",Age=" + String.valueOf(Age));
			sb.append(",Tranche_d_age=" + Tranche_d_age);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID, other.ID);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_AWC_JOB_datawarehouse = new byte[0];
		static byte[] commonByteArray_AWC_JOB_datawarehouse = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID;

		public int getID() {
			return this.ID;
		}

		public Integer Quantite_Commande;

		public Integer getQuantite_Commande() {
			return this.Quantite_Commande;
		}

		public Float Cout_standard;

		public Float getCout_standard() {
			return this.Cout_standard;
		}

		public String Nom_Client;

		public String getNom_Client() {
			return this.Nom_Client;
		}

		public String Ville;

		public String getVille() {
			return this.Ville;
		}

		public String Nom_Produit;

		public String getNom_Produit() {
			return this.Nom_Produit;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		public Float Prix;

		public Float getPrix() {
			return this.Prix;
		}

		public String Modele;

		public String getModele() {
			return this.Modele;
		}

		public String Sous_Categorie;

		public String getSous_Categorie() {
			return this.Sous_Categorie;
		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		public String Type_Business;

		public String getType_Business() {
			return this.Type_Business;
		}

		public String Type_Canal;

		public String getType_Canal() {
			return this.Type_Canal;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public String Continent;

		public String getContinent() {
			return this.Continent;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public String Etat_Civil;

		public String getEtat_Civil() {
			return this.Etat_Civil;
		}

		public String Genre;

		public String getGenre() {
			return this.Genre;
		}

		public Float Revenu_annuel;

		public Float getRevenu_annuel() {
			return this.Revenu_annuel;
		}

		public Integer Nbr_Enfants;

		public Integer getNbr_Enfants() {
			return this.Nbr_Enfants;
		}

		public String Poste;

		public String getPoste() {
			return this.Poste;
		}

		public String Propeietaire_Maison;

		public String getPropeietaire_Maison() {
			return this.Propeietaire_Maison;
		}

		public Integer Nbr_Voitures;

		public Integer getNbr_Voitures() {
			return this.Nbr_Voitures;
		}

		public String Annee;

		public String getAnnee() {
			return this.Annee;
		}

		public String Mois;

		public String getMois() {
			return this.Mois;
		}

		public String Semaine;

		public String getSemaine() {
			return this.Semaine;
		}

		public String Saison;

		public String getSaison() {
			return this.Saison;
		}

		public Integer Age;

		public Integer getAge() {
			return this.Age;
		}

		public String Tranche_d_age;

		public String getTranche_d_age() {
			return this.Tranche_d_age;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row3Struct other = (row3Struct) obj;

			if (this.ID != other.ID)
				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.ID = this.ID;
			other.Quantite_Commande = this.Quantite_Commande;
			other.Cout_standard = this.Cout_standard;
			other.Nom_Client = this.Nom_Client;
			other.Ville = this.Ville;
			other.Nom_Produit = this.Nom_Produit;
			other.Couleur = this.Couleur;
			other.Prix = this.Prix;
			other.Modele = this.Modele;
			other.Sous_Categorie = this.Sous_Categorie;
			other.Categorie = this.Categorie;
			other.Type_Business = this.Type_Business;
			other.Type_Canal = this.Type_Canal;
			other.Region = this.Region;
			other.Pays = this.Pays;
			other.Continent = this.Continent;
			other.Date = this.Date;
			other.Etat_Civil = this.Etat_Civil;
			other.Genre = this.Genre;
			other.Revenu_annuel = this.Revenu_annuel;
			other.Nbr_Enfants = this.Nbr_Enfants;
			other.Poste = this.Poste;
			other.Propeietaire_Maison = this.Propeietaire_Maison;
			other.Nbr_Voitures = this.Nbr_Voitures;
			other.Annee = this.Annee;
			other.Mois = this.Mois;
			other.Semaine = this.Semaine;
			other.Saison = this.Saison;
			other.Age = this.Age;
			other.Tranche_d_age = this.Tranche_d_age;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.ID = this.ID;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_datawarehouse.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_datawarehouse.length == 0) {
						commonByteArray_AWC_JOB_datawarehouse = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_datawarehouse = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AWC_JOB_datawarehouse, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_datawarehouse, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_datawarehouse.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_datawarehouse.length == 0) {
						commonByteArray_AWC_JOB_datawarehouse = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_datawarehouse = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AWC_JOB_datawarehouse, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_datawarehouse, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_AWC_JOB_datawarehouse) {

				try {

					int length = 0;

					this.ID = dis.readInt();

					this.Quantite_Commande = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Cout_standard = null;
					} else {
						this.Cout_standard = dis.readFloat();
					}

					this.Nom_Client = readString(dis);

					this.Ville = readString(dis);

					this.Nom_Produit = readString(dis);

					this.Couleur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix = null;
					} else {
						this.Prix = dis.readFloat();
					}

					this.Modele = readString(dis);

					this.Sous_Categorie = readString(dis);

					this.Categorie = readString(dis);

					this.Type_Business = readString(dis);

					this.Type_Canal = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Continent = readString(dis);

					this.Date = readDate(dis);

					this.Etat_Civil = readString(dis);

					this.Genre = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Revenu_annuel = null;
					} else {
						this.Revenu_annuel = dis.readFloat();
					}

					this.Nbr_Enfants = readInteger(dis);

					this.Poste = readString(dis);

					this.Propeietaire_Maison = readString(dis);

					this.Nbr_Voitures = readInteger(dis);

					this.Annee = readString(dis);

					this.Mois = readString(dis);

					this.Semaine = readString(dis);

					this.Saison = readString(dis);

					this.Age = readInteger(dis);

					this.Tranche_d_age = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_AWC_JOB_datawarehouse) {

				try {

					int length = 0;

					this.ID = dis.readInt();

					this.Quantite_Commande = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Cout_standard = null;
					} else {
						this.Cout_standard = dis.readFloat();
					}

					this.Nom_Client = readString(dis);

					this.Ville = readString(dis);

					this.Nom_Produit = readString(dis);

					this.Couleur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix = null;
					} else {
						this.Prix = dis.readFloat();
					}

					this.Modele = readString(dis);

					this.Sous_Categorie = readString(dis);

					this.Categorie = readString(dis);

					this.Type_Business = readString(dis);

					this.Type_Canal = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Continent = readString(dis);

					this.Date = readDate(dis);

					this.Etat_Civil = readString(dis);

					this.Genre = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Revenu_annuel = null;
					} else {
						this.Revenu_annuel = dis.readFloat();
					}

					this.Nbr_Enfants = readInteger(dis);

					this.Poste = readString(dis);

					this.Propeietaire_Maison = readString(dis);

					this.Nbr_Voitures = readInteger(dis);

					this.Annee = readString(dis);

					this.Mois = readString(dis);

					this.Semaine = readString(dis);

					this.Saison = readString(dis);

					this.Age = readInteger(dis);

					this.Tranche_d_age = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID);

				// Integer

				writeInteger(this.Quantite_Commande, dos);

				// Float

				if (this.Cout_standard == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Cout_standard);
				}

				// String

				writeString(this.Nom_Client, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Couleur, dos);

				// Float

				if (this.Prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix);
				}

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.Sous_Categorie, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Type_Business, dos);

				// String

				writeString(this.Type_Canal, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Continent, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.Etat_Civil, dos);

				// String

				writeString(this.Genre, dos);

				// Float

				if (this.Revenu_annuel == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Revenu_annuel);
				}

				// Integer

				writeInteger(this.Nbr_Enfants, dos);

				// String

				writeString(this.Poste, dos);

				// String

				writeString(this.Propeietaire_Maison, dos);

				// Integer

				writeInteger(this.Nbr_Voitures, dos);

				// String

				writeString(this.Annee, dos);

				// String

				writeString(this.Mois, dos);

				// String

				writeString(this.Semaine, dos);

				// String

				writeString(this.Saison, dos);

				// Integer

				writeInteger(this.Age, dos);

				// String

				writeString(this.Tranche_d_age, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID);

				// Integer

				writeInteger(this.Quantite_Commande, dos);

				// Float

				if (this.Cout_standard == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Cout_standard);
				}

				// String

				writeString(this.Nom_Client, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Couleur, dos);

				// Float

				if (this.Prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix);
				}

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.Sous_Categorie, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Type_Business, dos);

				// String

				writeString(this.Type_Canal, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Continent, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.Etat_Civil, dos);

				// String

				writeString(this.Genre, dos);

				// Float

				if (this.Revenu_annuel == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Revenu_annuel);
				}

				// Integer

				writeInteger(this.Nbr_Enfants, dos);

				// String

				writeString(this.Poste, dos);

				// String

				writeString(this.Propeietaire_Maison, dos);

				// Integer

				writeInteger(this.Nbr_Voitures, dos);

				// String

				writeString(this.Annee, dos);

				// String

				writeString(this.Mois, dos);

				// String

				writeString(this.Semaine, dos);

				// String

				writeString(this.Saison, dos);

				// Integer

				writeInteger(this.Age, dos);

				// String

				writeString(this.Tranche_d_age, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID=" + String.valueOf(ID));
			sb.append(",Quantite_Commande=" + String.valueOf(Quantite_Commande));
			sb.append(",Cout_standard=" + String.valueOf(Cout_standard));
			sb.append(",Nom_Client=" + Nom_Client);
			sb.append(",Ville=" + Ville);
			sb.append(",Nom_Produit=" + Nom_Produit);
			sb.append(",Couleur=" + Couleur);
			sb.append(",Prix=" + String.valueOf(Prix));
			sb.append(",Modele=" + Modele);
			sb.append(",Sous_Categorie=" + Sous_Categorie);
			sb.append(",Categorie=" + Categorie);
			sb.append(",Type_Business=" + Type_Business);
			sb.append(",Type_Canal=" + Type_Canal);
			sb.append(",Region=" + Region);
			sb.append(",Pays=" + Pays);
			sb.append(",Continent=" + Continent);
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",Etat_Civil=" + Etat_Civil);
			sb.append(",Genre=" + Genre);
			sb.append(",Revenu_annuel=" + String.valueOf(Revenu_annuel));
			sb.append(",Nbr_Enfants=" + String.valueOf(Nbr_Enfants));
			sb.append(",Poste=" + Poste);
			sb.append(",Propeietaire_Maison=" + Propeietaire_Maison);
			sb.append(",Nbr_Voitures=" + String.valueOf(Nbr_Voitures));
			sb.append(",Annee=" + Annee);
			sb.append(",Mois=" + Mois);
			sb.append(",Semaine=" + Semaine);
			sb.append(",Saison=" + Saison);
			sb.append(",Age=" + String.valueOf(Age));
			sb.append(",Tranche_d_age=" + Tranche_d_age);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID, other.ID);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_AWC_JOB_datawarehouse = new byte[0];
		static byte[] commonByteArray_AWC_JOB_datawarehouse = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID;

		public int getID() {
			return this.ID;
		}

		public Integer Quantite_Commande;

		public Integer getQuantite_Commande() {
			return this.Quantite_Commande;
		}

		public Float Cout_standard;

		public Float getCout_standard() {
			return this.Cout_standard;
		}

		public String Nom_Client;

		public String getNom_Client() {
			return this.Nom_Client;
		}

		public String Ville;

		public String getVille() {
			return this.Ville;
		}

		public String Nom_Produit;

		public String getNom_Produit() {
			return this.Nom_Produit;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		public Float Prix;

		public Float getPrix() {
			return this.Prix;
		}

		public String Modele;

		public String getModele() {
			return this.Modele;
		}

		public String Sous_Categorie;

		public String getSous_Categorie() {
			return this.Sous_Categorie;
		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		public String Type_Business;

		public String getType_Business() {
			return this.Type_Business;
		}

		public String Type_Canal;

		public String getType_Canal() {
			return this.Type_Canal;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public String Continent;

		public String getContinent() {
			return this.Continent;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public String Etat_Civil;

		public String getEtat_Civil() {
			return this.Etat_Civil;
		}

		public String Genre;

		public String getGenre() {
			return this.Genre;
		}

		public Float Revenu_annuel;

		public Float getRevenu_annuel() {
			return this.Revenu_annuel;
		}

		public Integer Nbr_Enfants;

		public Integer getNbr_Enfants() {
			return this.Nbr_Enfants;
		}

		public String Poste;

		public String getPoste() {
			return this.Poste;
		}

		public String Propeietaire_Maison;

		public String getPropeietaire_Maison() {
			return this.Propeietaire_Maison;
		}

		public Integer Nbr_Voitures;

		public Integer getNbr_Voitures() {
			return this.Nbr_Voitures;
		}

		public String Annee;

		public String getAnnee() {
			return this.Annee;
		}

		public String Mois;

		public String getMois() {
			return this.Mois;
		}

		public String Semaine;

		public String getSemaine() {
			return this.Semaine;
		}

		public String Saison;

		public String getSaison() {
			return this.Saison;
		}

		public Integer Age;

		public Integer getAge() {
			return this.Age;
		}

		public String Tranche_d_age;

		public String getTranche_d_age() {
			return this.Tranche_d_age;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row4Struct other = (row4Struct) obj;

			if (this.ID != other.ID)
				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.ID = this.ID;
			other.Quantite_Commande = this.Quantite_Commande;
			other.Cout_standard = this.Cout_standard;
			other.Nom_Client = this.Nom_Client;
			other.Ville = this.Ville;
			other.Nom_Produit = this.Nom_Produit;
			other.Couleur = this.Couleur;
			other.Prix = this.Prix;
			other.Modele = this.Modele;
			other.Sous_Categorie = this.Sous_Categorie;
			other.Categorie = this.Categorie;
			other.Type_Business = this.Type_Business;
			other.Type_Canal = this.Type_Canal;
			other.Region = this.Region;
			other.Pays = this.Pays;
			other.Continent = this.Continent;
			other.Date = this.Date;
			other.Etat_Civil = this.Etat_Civil;
			other.Genre = this.Genre;
			other.Revenu_annuel = this.Revenu_annuel;
			other.Nbr_Enfants = this.Nbr_Enfants;
			other.Poste = this.Poste;
			other.Propeietaire_Maison = this.Propeietaire_Maison;
			other.Nbr_Voitures = this.Nbr_Voitures;
			other.Annee = this.Annee;
			other.Mois = this.Mois;
			other.Semaine = this.Semaine;
			other.Saison = this.Saison;
			other.Age = this.Age;
			other.Tranche_d_age = this.Tranche_d_age;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.ID = this.ID;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_datawarehouse.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_datawarehouse.length == 0) {
						commonByteArray_AWC_JOB_datawarehouse = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_datawarehouse = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AWC_JOB_datawarehouse, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_datawarehouse, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_datawarehouse.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_datawarehouse.length == 0) {
						commonByteArray_AWC_JOB_datawarehouse = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_datawarehouse = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AWC_JOB_datawarehouse, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_datawarehouse, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_AWC_JOB_datawarehouse) {

				try {

					int length = 0;

					this.ID = dis.readInt();

					this.Quantite_Commande = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Cout_standard = null;
					} else {
						this.Cout_standard = dis.readFloat();
					}

					this.Nom_Client = readString(dis);

					this.Ville = readString(dis);

					this.Nom_Produit = readString(dis);

					this.Couleur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix = null;
					} else {
						this.Prix = dis.readFloat();
					}

					this.Modele = readString(dis);

					this.Sous_Categorie = readString(dis);

					this.Categorie = readString(dis);

					this.Type_Business = readString(dis);

					this.Type_Canal = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Continent = readString(dis);

					this.Date = readDate(dis);

					this.Etat_Civil = readString(dis);

					this.Genre = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Revenu_annuel = null;
					} else {
						this.Revenu_annuel = dis.readFloat();
					}

					this.Nbr_Enfants = readInteger(dis);

					this.Poste = readString(dis);

					this.Propeietaire_Maison = readString(dis);

					this.Nbr_Voitures = readInteger(dis);

					this.Annee = readString(dis);

					this.Mois = readString(dis);

					this.Semaine = readString(dis);

					this.Saison = readString(dis);

					this.Age = readInteger(dis);

					this.Tranche_d_age = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_AWC_JOB_datawarehouse) {

				try {

					int length = 0;

					this.ID = dis.readInt();

					this.Quantite_Commande = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Cout_standard = null;
					} else {
						this.Cout_standard = dis.readFloat();
					}

					this.Nom_Client = readString(dis);

					this.Ville = readString(dis);

					this.Nom_Produit = readString(dis);

					this.Couleur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix = null;
					} else {
						this.Prix = dis.readFloat();
					}

					this.Modele = readString(dis);

					this.Sous_Categorie = readString(dis);

					this.Categorie = readString(dis);

					this.Type_Business = readString(dis);

					this.Type_Canal = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Continent = readString(dis);

					this.Date = readDate(dis);

					this.Etat_Civil = readString(dis);

					this.Genre = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Revenu_annuel = null;
					} else {
						this.Revenu_annuel = dis.readFloat();
					}

					this.Nbr_Enfants = readInteger(dis);

					this.Poste = readString(dis);

					this.Propeietaire_Maison = readString(dis);

					this.Nbr_Voitures = readInteger(dis);

					this.Annee = readString(dis);

					this.Mois = readString(dis);

					this.Semaine = readString(dis);

					this.Saison = readString(dis);

					this.Age = readInteger(dis);

					this.Tranche_d_age = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID);

				// Integer

				writeInteger(this.Quantite_Commande, dos);

				// Float

				if (this.Cout_standard == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Cout_standard);
				}

				// String

				writeString(this.Nom_Client, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Couleur, dos);

				// Float

				if (this.Prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix);
				}

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.Sous_Categorie, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Type_Business, dos);

				// String

				writeString(this.Type_Canal, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Continent, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.Etat_Civil, dos);

				// String

				writeString(this.Genre, dos);

				// Float

				if (this.Revenu_annuel == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Revenu_annuel);
				}

				// Integer

				writeInteger(this.Nbr_Enfants, dos);

				// String

				writeString(this.Poste, dos);

				// String

				writeString(this.Propeietaire_Maison, dos);

				// Integer

				writeInteger(this.Nbr_Voitures, dos);

				// String

				writeString(this.Annee, dos);

				// String

				writeString(this.Mois, dos);

				// String

				writeString(this.Semaine, dos);

				// String

				writeString(this.Saison, dos);

				// Integer

				writeInteger(this.Age, dos);

				// String

				writeString(this.Tranche_d_age, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID);

				// Integer

				writeInteger(this.Quantite_Commande, dos);

				// Float

				if (this.Cout_standard == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Cout_standard);
				}

				// String

				writeString(this.Nom_Client, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Nom_Produit, dos);

				// String

				writeString(this.Couleur, dos);

				// Float

				if (this.Prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix);
				}

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.Sous_Categorie, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Type_Business, dos);

				// String

				writeString(this.Type_Canal, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Continent, dos);

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.Etat_Civil, dos);

				// String

				writeString(this.Genre, dos);

				// Float

				if (this.Revenu_annuel == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Revenu_annuel);
				}

				// Integer

				writeInteger(this.Nbr_Enfants, dos);

				// String

				writeString(this.Poste, dos);

				// String

				writeString(this.Propeietaire_Maison, dos);

				// Integer

				writeInteger(this.Nbr_Voitures, dos);

				// String

				writeString(this.Annee, dos);

				// String

				writeString(this.Mois, dos);

				// String

				writeString(this.Semaine, dos);

				// String

				writeString(this.Saison, dos);

				// Integer

				writeInteger(this.Age, dos);

				// String

				writeString(this.Tranche_d_age, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID=" + String.valueOf(ID));
			sb.append(",Quantite_Commande=" + String.valueOf(Quantite_Commande));
			sb.append(",Cout_standard=" + String.valueOf(Cout_standard));
			sb.append(",Nom_Client=" + Nom_Client);
			sb.append(",Ville=" + Ville);
			sb.append(",Nom_Produit=" + Nom_Produit);
			sb.append(",Couleur=" + Couleur);
			sb.append(",Prix=" + String.valueOf(Prix));
			sb.append(",Modele=" + Modele);
			sb.append(",Sous_Categorie=" + Sous_Categorie);
			sb.append(",Categorie=" + Categorie);
			sb.append(",Type_Business=" + Type_Business);
			sb.append(",Type_Canal=" + Type_Canal);
			sb.append(",Region=" + Region);
			sb.append(",Pays=" + Pays);
			sb.append(",Continent=" + Continent);
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",Etat_Civil=" + Etat_Civil);
			sb.append(",Genre=" + Genre);
			sb.append(",Revenu_annuel=" + String.valueOf(Revenu_annuel));
			sb.append(",Nbr_Enfants=" + String.valueOf(Nbr_Enfants));
			sb.append(",Poste=" + Poste);
			sb.append(",Propeietaire_Maison=" + Propeietaire_Maison);
			sb.append(",Nbr_Voitures=" + String.valueOf(Nbr_Voitures));
			sb.append(",Annee=" + Annee);
			sb.append(",Mois=" + Mois);
			sb.append(",Semaine=" + Semaine);
			sb.append(",Saison=" + Saison);
			sb.append(",Age=" + String.valueOf(Age));
			sb.append(",Tranche_d_age=" + Tranche_d_age);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID, other.ID);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();

				row2Struct row2 = new row2Struct();

				row4Struct row4 = new row4Struct();

				row3Struct row3 = new row3Struct();

				row5Struct row5 = new row5Struct();

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tDBOutput_1 = 0;

				String dbschema_tDBOutput_1 = null;
				dbschema_tDBOutput_1 = "public";

				String tableName_tDBOutput_1 = null;
				if (dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
					tableName_tDBOutput_1 = ("data");
				} else {
					tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "\".\"" + ("data");
				}

				int updateKeyCount_tDBOutput_1 = 1;
				if (updateKeyCount_tDBOutput_1 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_1 == 30 && true) {
					System.err.println("For update, every Schema column can not be a key");
				}

				int nb_line_tDBOutput_1 = 0;
				int nb_line_update_tDBOutput_1 = 0;
				int nb_line_inserted_tDBOutput_1 = 0;
				int nb_line_deleted_tDBOutput_1 = 0;
				int nb_line_rejected_tDBOutput_1 = 0;

				int deletedCount_tDBOutput_1 = 0;
				int updatedCount_tDBOutput_1 = 0;
				int insertedCount_tDBOutput_1 = 0;
				int rowsToCommitCount_tDBOutput_1 = 0;
				int rejectedCount_tDBOutput_1 = 0;

				boolean whetherReject_tDBOutput_1 = false;

				java.sql.Connection conn_tDBOutput_1 = null;
				String dbUser_tDBOutput_1 = null;

				java.lang.Class.forName("org.postgresql.Driver");

				String url_tDBOutput_1 = "jdbc:postgresql://" + "localhost" + ":" + "5432" + "/" + "aw_datawarehouse";
				dbUser_tDBOutput_1 = "postgres";

				final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:rF1MIXjX4qxbjYeK/1SIWbavQUjrJQgNcyUpgifbqRhMbjK19w==");

				String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;

				conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1,
						dbPwd_tDBOutput_1);

				resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
				conn_tDBOutput_1.setAutoCommit(false);
				int commitEvery_tDBOutput_1 = 10000;
				int commitCounter_tDBOutput_1 = 0;

				int count_tDBOutput_1 = 0;
				java.sql.DatabaseMetaData dbMetaData_tDBOutput_1 = conn_tDBOutput_1.getMetaData();
				boolean whetherExist_tDBOutput_1 = false;
				try (java.sql.ResultSet rsTable_tDBOutput_1 = dbMetaData_tDBOutput_1.getTables(null, null, null,
						new String[] { "TABLE" })) {
					String defaultSchema_tDBOutput_1 = "public";
					if (dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
						try (java.sql.Statement stmtSchema_tDBOutput_1 = conn_tDBOutput_1.createStatement();
								java.sql.ResultSet rsSchema_tDBOutput_1 = stmtSchema_tDBOutput_1
										.executeQuery("select current_schema() ")) {
							while (rsSchema_tDBOutput_1.next()) {
								defaultSchema_tDBOutput_1 = rsSchema_tDBOutput_1.getString("current_schema");
							}
						}
					}
					while (rsTable_tDBOutput_1.next()) {
						String table_tDBOutput_1 = rsTable_tDBOutput_1.getString("TABLE_NAME");
						String schema_tDBOutput_1 = rsTable_tDBOutput_1.getString("TABLE_SCHEM");
						if (table_tDBOutput_1.equals(("data")) && (schema_tDBOutput_1.equals(dbschema_tDBOutput_1)
								|| ((dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0)
										&& defaultSchema_tDBOutput_1.equals(schema_tDBOutput_1)))) {
							whetherExist_tDBOutput_1 = true;
							break;
						}
					}
				}
				if (whetherExist_tDBOutput_1) {
					try (java.sql.Statement stmtDrop_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
						stmtDrop_tDBOutput_1.execute("DROP TABLE \"" + tableName_tDBOutput_1 + "\"");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
					stmtCreate_tDBOutput_1.execute("CREATE TABLE \"" + tableName_tDBOutput_1
							+ "\"(\"ID\" INT4  not null ,\"Quantite Commande\" INT4 ,\"Cout standard\" FLOAT4 ,\"Nom Client\" TEXT ,\"Ville\" TEXT ,\"Nom Produit\" TEXT ,\"Couleur\" TEXT ,\"Prix\" FLOAT4 ,\"Modele\" TEXT ,\"Sous Categorie\" TEXT ,\"Categorie\" TEXT ,\"Type Business\" TEXT ,\"Type Canal\" TEXT ,\"Region\" TEXT ,\"Pays\" TEXT ,\"Continent\" TEXT ,\"Date\" DATE  not null ,\"Etat Civil\" TEXT ,\"Genre\" TEXT ,\"Revenu annuel\" FLOAT4 ,\"Nbr Enfants\" INT4 ,\"Poste\" TEXT ,\"Propeietaire Maison\" TEXT ,\"Nbr Voitures\" INT4 ,\"Annee\" TEXT ,\"Mois\" TEXT ,\"Semaine\" TEXT ,\"Saison\" TEXT ,\"Age\" INT4 ,\"Tranche d'âge\" TEXT ,primary key(\"ID\"))");
				}
				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement("SELECT COUNT(1) FROM \"" + tableName_tDBOutput_1 + "\" WHERE \"ID\" = ?");
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);
				String insert_tDBOutput_1 = "INSERT INTO \"" + tableName_tDBOutput_1
						+ "\" (\"ID\",\"Quantite Commande\",\"Cout standard\",\"Nom Client\",\"Ville\",\"Nom Produit\",\"Couleur\",\"Prix\",\"Modele\",\"Sous Categorie\",\"Categorie\",\"Type Business\",\"Type Canal\",\"Region\",\"Pays\",\"Continent\",\"Date\",\"Etat Civil\",\"Genre\",\"Revenu annuel\",\"Nbr Enfants\",\"Poste\",\"Propeietaire Maison\",\"Nbr Voitures\",\"Annee\",\"Mois\",\"Semaine\",\"Saison\",\"Age\",\"Tranche d'âge\") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmtInsert_tDBOutput_1", pstmtInsert_tDBOutput_1);
				String update_tDBOutput_1 = "UPDATE \"" + tableName_tDBOutput_1
						+ "\" SET \"Quantite Commande\" = ?,\"Cout standard\" = ?,\"Nom Client\" = ?,\"Ville\" = ?,\"Nom Produit\" = ?,\"Couleur\" = ?,\"Prix\" = ?,\"Modele\" = ?,\"Sous Categorie\" = ?,\"Categorie\" = ?,\"Type Business\" = ?,\"Type Canal\" = ?,\"Region\" = ?,\"Pays\" = ?,\"Continent\" = ?,\"Date\" = ?,\"Etat Civil\" = ?,\"Genre\" = ?,\"Revenu annuel\" = ?,\"Nbr Enfants\" = ?,\"Poste\" = ?,\"Propeietaire Maison\" = ?,\"Nbr Voitures\" = ?,\"Annee\" = ?,\"Mois\" = ?,\"Semaine\" = ?,\"Saison\" = ?,\"Age\" = ?,\"Tranche d'âge\" = ? WHERE \"ID\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement(update_tDBOutput_1);
				resourceMap.put("pstmtUpdate_tDBOutput_1", pstmtUpdate_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tUnite_1 begin ] start
				 */

				ok_Hash.put("tUnite_1", false);
				start_Hash.put("tUnite_1", System.currentTimeMillis());

				currentComponent = "tUnite_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1", "row4", "row3", "row2");
				}

				int tos_count_tUnite_1 = 0;

				int nb_line_tUnite_1 = 0;

				/**
				 * [tUnite_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "C:/Users/hamza/OneDrive/Desktop/Data/bi_adventurework/Data/Adventure_works2017.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/hamza/OneDrive/Desktop/Data/bi_adventurework/Data/Adventure_works2017.csv",
								"UTF-8", ";", "\n", false, 1, 0, limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row1 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row1 = new row1Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.ID = ParserUtils.parseTo_int(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ID", "row1", temp, ex_tFileInputDelimited_1), ex_tFileInputDelimited_1));
								}

							} else {

								rowstate_tFileInputDelimited_1.setException(new RuntimeException(
										"Value is empty for column : 'ID' in 'row1' connection, value is invalid or this column should be nullable or have a default value."));

							}

							columnIndexWithD_tFileInputDelimited_1 = 1;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Quantite_Commande = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Quantite_Commande", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Quantite_Commande = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 2;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Cout_standard = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Cout_standard", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Cout_standard = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row1.Nom_Client = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							row1.Ville = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 5;

							row1.Nom_Produit = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 6;

							row1.Couleur = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 7;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Prix = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Prix", "row1", temp, ex_tFileInputDelimited_1), ex_tFileInputDelimited_1));
								}

							} else {

								row1.Prix = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 8;

							row1.Modele = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 9;

							row1.Sous_Categorie = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 10;

							row1.Categorie = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 11;

							row1.Type_Business = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 12;

							row1.Type_Canal = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 13;

							row1.Region = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 14;

							row1.Pays = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 15;

							row1.Continent = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 16;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Date = ParserUtils.parseTo_Date(temp, "yyyy-MM-dd");

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Date", "row1", temp, ex_tFileInputDelimited_1), ex_tFileInputDelimited_1));
								}

							} else {

								row1.Date = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 17;

							row1.Etat_Civil = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 18;

							row1.Genre = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 19;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Revenu_annuel = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Revenu_annuel", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Revenu_annuel = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 20;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Nbr_Enfants = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Nbr_Enfants", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Nbr_Enfants = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 21;

							row1.Poste = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 22;

							row1.Propeietaire_Maison = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 23;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Nbr_Voitures = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Nbr_Voitures", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Nbr_Voitures = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 24;

							row1.Annee = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 25;

							row1.Mois = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 26;

							row1.Semaine = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 27;

							row1.Saison = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 28;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Age = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Age", "row1", temp, ex_tFileInputDelimited_1), ex_tFileInputDelimited_1));
								}

							} else {

								row1.Age = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 29;

							row1.Tranche_d_age = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row1 = null;

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row1"
						if (row1 != null) {

							/**
							 * [tUnite_1 main ] start
							 */

							currentComponent = "tUnite_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

//////////

// for output
							row5 = new row5Struct();

							row5.ID = row1.ID;
							row5.Quantite_Commande = row1.Quantite_Commande;
							row5.Cout_standard = row1.Cout_standard;
							row5.Nom_Client = row1.Nom_Client;
							row5.Ville = row1.Ville;
							row5.Nom_Produit = row1.Nom_Produit;
							row5.Couleur = row1.Couleur;
							row5.Prix = row1.Prix;
							row5.Modele = row1.Modele;
							row5.Sous_Categorie = row1.Sous_Categorie;
							row5.Categorie = row1.Categorie;
							row5.Type_Business = row1.Type_Business;
							row5.Type_Canal = row1.Type_Canal;
							row5.Region = row1.Region;
							row5.Pays = row1.Pays;
							row5.Continent = row1.Continent;
							row5.Date = row1.Date;
							row5.Etat_Civil = row1.Etat_Civil;
							row5.Genre = row1.Genre;
							row5.Revenu_annuel = row1.Revenu_annuel;
							row5.Nbr_Enfants = row1.Nbr_Enfants;
							row5.Poste = row1.Poste;
							row5.Propeietaire_Maison = row1.Propeietaire_Maison;
							row5.Nbr_Voitures = row1.Nbr_Voitures;
							row5.Annee = row1.Annee;
							row5.Mois = row1.Mois;
							row5.Semaine = row1.Semaine;
							row5.Saison = row1.Saison;
							row5.Age = row1.Age;
							row5.Tranche_d_age = row1.Tranche_d_age;

							nb_line_tUnite_1++;

//////////

							tos_count_tUnite_1++;

							/**
							 * [tUnite_1 main ] stop
							 */

							/**
							 * [tUnite_1 process_data_begin ] start
							 */

							currentComponent = "tUnite_1";

							/**
							 * [tUnite_1 process_data_begin ] stop
							 */

							/**
							 * [tDBOutput_1 main ] start
							 */

							currentComponent = "tDBOutput_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row5"

								);
							}

							whetherReject_tDBOutput_1 = false;
							pstmt_tDBOutput_1.setInt(1, row5.ID);

							int checkCount_tDBOutput_1 = -1;
							try (java.sql.ResultSet rs_tDBOutput_1 = pstmt_tDBOutput_1.executeQuery()) {
								while (rs_tDBOutput_1.next()) {
									checkCount_tDBOutput_1 = rs_tDBOutput_1.getInt(1);
								}
							}
							if (checkCount_tDBOutput_1 > 0) {
								if (row5.Quantite_Commande == null) {
									pstmtUpdate_tDBOutput_1.setNull(1, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_1.setInt(1, row5.Quantite_Commande);
								}

								if (row5.Cout_standard == null) {
									pstmtUpdate_tDBOutput_1.setNull(2, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_1.setFloat(2, row5.Cout_standard);
								}

								if (row5.Nom_Client == null) {
									pstmtUpdate_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(3, row5.Nom_Client);
								}

								if (row5.Ville == null) {
									pstmtUpdate_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(4, row5.Ville);
								}

								if (row5.Nom_Produit == null) {
									pstmtUpdate_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(5, row5.Nom_Produit);
								}

								if (row5.Couleur == null) {
									pstmtUpdate_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(6, row5.Couleur);
								}

								if (row5.Prix == null) {
									pstmtUpdate_tDBOutput_1.setNull(7, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_1.setFloat(7, row5.Prix);
								}

								if (row5.Modele == null) {
									pstmtUpdate_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(8, row5.Modele);
								}

								if (row5.Sous_Categorie == null) {
									pstmtUpdate_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(9, row5.Sous_Categorie);
								}

								if (row5.Categorie == null) {
									pstmtUpdate_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(10, row5.Categorie);
								}

								if (row5.Type_Business == null) {
									pstmtUpdate_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(11, row5.Type_Business);
								}

								if (row5.Type_Canal == null) {
									pstmtUpdate_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(12, row5.Type_Canal);
								}

								if (row5.Region == null) {
									pstmtUpdate_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(13, row5.Region);
								}

								if (row5.Pays == null) {
									pstmtUpdate_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(14, row5.Pays);
								}

								if (row5.Continent == null) {
									pstmtUpdate_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(15, row5.Continent);
								}

								if (row5.Date != null) {
									pstmtUpdate_tDBOutput_1.setTimestamp(16,
											new java.sql.Timestamp(row5.Date.getTime()));
								} else {
									pstmtUpdate_tDBOutput_1.setNull(16, java.sql.Types.TIMESTAMP);
								}

								if (row5.Etat_Civil == null) {
									pstmtUpdate_tDBOutput_1.setNull(17, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(17, row5.Etat_Civil);
								}

								if (row5.Genre == null) {
									pstmtUpdate_tDBOutput_1.setNull(18, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(18, row5.Genre);
								}

								if (row5.Revenu_annuel == null) {
									pstmtUpdate_tDBOutput_1.setNull(19, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_1.setFloat(19, row5.Revenu_annuel);
								}

								if (row5.Nbr_Enfants == null) {
									pstmtUpdate_tDBOutput_1.setNull(20, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_1.setInt(20, row5.Nbr_Enfants);
								}

								if (row5.Poste == null) {
									pstmtUpdate_tDBOutput_1.setNull(21, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(21, row5.Poste);
								}

								if (row5.Propeietaire_Maison == null) {
									pstmtUpdate_tDBOutput_1.setNull(22, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(22, row5.Propeietaire_Maison);
								}

								if (row5.Nbr_Voitures == null) {
									pstmtUpdate_tDBOutput_1.setNull(23, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_1.setInt(23, row5.Nbr_Voitures);
								}

								if (row5.Annee == null) {
									pstmtUpdate_tDBOutput_1.setNull(24, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(24, row5.Annee);
								}

								if (row5.Mois == null) {
									pstmtUpdate_tDBOutput_1.setNull(25, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(25, row5.Mois);
								}

								if (row5.Semaine == null) {
									pstmtUpdate_tDBOutput_1.setNull(26, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(26, row5.Semaine);
								}

								if (row5.Saison == null) {
									pstmtUpdate_tDBOutput_1.setNull(27, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(27, row5.Saison);
								}

								if (row5.Age == null) {
									pstmtUpdate_tDBOutput_1.setNull(28, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_1.setInt(28, row5.Age);
								}

								if (row5.Tranche_d_age == null) {
									pstmtUpdate_tDBOutput_1.setNull(29, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(29, row5.Tranche_d_age);
								}

								pstmtUpdate_tDBOutput_1.setInt(30 + count_tDBOutput_1, row5.ID);

								try {

									int processedCount_tDBOutput_1 = pstmtUpdate_tDBOutput_1.executeUpdate();
									updatedCount_tDBOutput_1 += processedCount_tDBOutput_1;
									rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
									nb_line_tDBOutput_1++;

								} catch (java.lang.Exception e) {
									globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

									whetherReject_tDBOutput_1 = true;
									nb_line_tDBOutput_1++;
									System.err.print(e.getMessage());
								}
							} else {
								pstmtInsert_tDBOutput_1.setInt(1, row5.ID);

								if (row5.Quantite_Commande == null) {
									pstmtInsert_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_1.setInt(2, row5.Quantite_Commande);
								}

								if (row5.Cout_standard == null) {
									pstmtInsert_tDBOutput_1.setNull(3, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_1.setFloat(3, row5.Cout_standard);
								}

								if (row5.Nom_Client == null) {
									pstmtInsert_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(4, row5.Nom_Client);
								}

								if (row5.Ville == null) {
									pstmtInsert_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(5, row5.Ville);
								}

								if (row5.Nom_Produit == null) {
									pstmtInsert_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(6, row5.Nom_Produit);
								}

								if (row5.Couleur == null) {
									pstmtInsert_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(7, row5.Couleur);
								}

								if (row5.Prix == null) {
									pstmtInsert_tDBOutput_1.setNull(8, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_1.setFloat(8, row5.Prix);
								}

								if (row5.Modele == null) {
									pstmtInsert_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(9, row5.Modele);
								}

								if (row5.Sous_Categorie == null) {
									pstmtInsert_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(10, row5.Sous_Categorie);
								}

								if (row5.Categorie == null) {
									pstmtInsert_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(11, row5.Categorie);
								}

								if (row5.Type_Business == null) {
									pstmtInsert_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(12, row5.Type_Business);
								}

								if (row5.Type_Canal == null) {
									pstmtInsert_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(13, row5.Type_Canal);
								}

								if (row5.Region == null) {
									pstmtInsert_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(14, row5.Region);
								}

								if (row5.Pays == null) {
									pstmtInsert_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(15, row5.Pays);
								}

								if (row5.Continent == null) {
									pstmtInsert_tDBOutput_1.setNull(16, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(16, row5.Continent);
								}

								if (row5.Date != null) {
									pstmtInsert_tDBOutput_1.setTimestamp(17,
											new java.sql.Timestamp(row5.Date.getTime()));
								} else {
									pstmtInsert_tDBOutput_1.setNull(17, java.sql.Types.TIMESTAMP);
								}

								if (row5.Etat_Civil == null) {
									pstmtInsert_tDBOutput_1.setNull(18, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(18, row5.Etat_Civil);
								}

								if (row5.Genre == null) {
									pstmtInsert_tDBOutput_1.setNull(19, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(19, row5.Genre);
								}

								if (row5.Revenu_annuel == null) {
									pstmtInsert_tDBOutput_1.setNull(20, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_1.setFloat(20, row5.Revenu_annuel);
								}

								if (row5.Nbr_Enfants == null) {
									pstmtInsert_tDBOutput_1.setNull(21, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_1.setInt(21, row5.Nbr_Enfants);
								}

								if (row5.Poste == null) {
									pstmtInsert_tDBOutput_1.setNull(22, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(22, row5.Poste);
								}

								if (row5.Propeietaire_Maison == null) {
									pstmtInsert_tDBOutput_1.setNull(23, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(23, row5.Propeietaire_Maison);
								}

								if (row5.Nbr_Voitures == null) {
									pstmtInsert_tDBOutput_1.setNull(24, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_1.setInt(24, row5.Nbr_Voitures);
								}

								if (row5.Annee == null) {
									pstmtInsert_tDBOutput_1.setNull(25, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(25, row5.Annee);
								}

								if (row5.Mois == null) {
									pstmtInsert_tDBOutput_1.setNull(26, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(26, row5.Mois);
								}

								if (row5.Semaine == null) {
									pstmtInsert_tDBOutput_1.setNull(27, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(27, row5.Semaine);
								}

								if (row5.Saison == null) {
									pstmtInsert_tDBOutput_1.setNull(28, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(28, row5.Saison);
								}

								if (row5.Age == null) {
									pstmtInsert_tDBOutput_1.setNull(29, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_1.setInt(29, row5.Age);
								}

								if (row5.Tranche_d_age == null) {
									pstmtInsert_tDBOutput_1.setNull(30, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(30, row5.Tranche_d_age);
								}

								try {

									int processedCount_tDBOutput_1 = pstmtInsert_tDBOutput_1.executeUpdate();
									insertedCount_tDBOutput_1 += processedCount_tDBOutput_1;
									rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
									nb_line_tDBOutput_1++;

								} catch (java.lang.Exception e) {
									globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

									whetherReject_tDBOutput_1 = true;
									nb_line_tDBOutput_1++;
									System.err.print(e.getMessage());
								}
							}
							commitCounter_tDBOutput_1++;
							if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {
								if (rowsToCommitCount_tDBOutput_1 != 0) {

								}
								conn_tDBOutput_1.commit();
								if (rowsToCommitCount_tDBOutput_1 != 0) {

									rowsToCommitCount_tDBOutput_1 = 0;
								}
								commitCounter_tDBOutput_1 = 0;
							}

							tos_count_tDBOutput_1++;

							/**
							 * [tDBOutput_1 main ] stop
							 */

							/**
							 * [tDBOutput_1 process_data_begin ] start
							 */

							currentComponent = "tDBOutput_1";

							/**
							 * [tDBOutput_1 process_data_begin ] stop
							 */

							/**
							 * [tDBOutput_1 process_data_end ] start
							 */

							currentComponent = "tDBOutput_1";

							/**
							 * [tDBOutput_1 process_data_end ] stop
							 */

							/**
							 * [tUnite_1 process_data_end ] start
							 */

							currentComponent = "tUnite_1";

							/**
							 * [tUnite_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

					}
				} finally {
					if (!((Object) ("C:/Users/hamza/OneDrive/Desktop/Data/bi_adventurework/Data/Adventure_works2017.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tDBInput_1 begin ] start
				 */

				ok_Hash.put("tDBInput_1", false);
				start_Hash.put("tDBInput_1", System.currentTimeMillis());

				currentComponent = "tDBInput_1";

				int tos_count_tDBInput_1 = 0;

				int nb_line_tDBInput_1 = 0;
				java.sql.Connection conn_tDBInput_1 = null;
				String driverClass_tDBInput_1 = "org.postgresql.Driver";
				java.lang.Class jdbcclazz_tDBInput_1 = java.lang.Class.forName(driverClass_tDBInput_1);
				String dbUser_tDBInput_1 = "postgres";

				final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:EP8YKQoREQSSWF/7QFRUhSdgRH4ozK++SqwZSnid+nA0jvdmvQ==");

				String dbPwd_tDBInput_1 = decryptedPassword_tDBInput_1;

				String url_tDBInput_1 = "jdbc:postgresql://" + "localhost" + ":" + "5432" + "/" + "adventurework";

				conn_tDBInput_1 = java.sql.DriverManager.getConnection(url_tDBInput_1, dbUser_tDBInput_1,
						dbPwd_tDBInput_1);

				conn_tDBInput_1.setAutoCommit(false);

				java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

				String dbquery_tDBInput_1 = "SELECT \n  \"public\".\"adventurework\".\"index\", \n  \"public\".\"adventurework\".\"Quantite Commande\", \n  \"public\"."
						+ "\"adventurework\".\"Cout standard\", \n  \"public\".\"adventurework\".\"Nom Client\", \n  \"public\".\"adventurework\".\"V"
						+ "ille\", \n  \"public\".\"adventurework\".\"Nom Produit\", \n  \"public\".\"adventurework\".\"Couleur\", \n  \"public\".\"ad"
						+ "venturework\".\"Prix\", \n  \"public\".\"adventurework\".\"Modele\", \n  \"public\".\"adventurework\".\"Sous Categorie\", "
						+ "\n  \"public\".\"adventurework\".\"Categorie\", \n  \"public\".\"adventurework\".\"Type Business\", \n  \"public\".\"advent"
						+ "urework\".\"Type Canal\", \n  \"public\".\"adventurework\".\"Region\", \n  \"public\".\"adventurework\".\"Pays\", \n  \"pub"
						+ "lic\".\"adventurework\".\"Continent\", \n  \"public\".\"adventurework\".\"Date\", \n  \"public\".\"adventurework\".\"Etat "
						+ "Civil\", \n  \"public\".\"adventurework\".\"Genre\", \n  \"public\".\"adventurework\".\"Revenu annuel\", \n  \"public\".\"a"
						+ "dventurework\".\"Nbr Enfants\", \n  \"public\".\"adventurework\".\"Poste\", \n  \"public\".\"adventurework\".\"Propeietair"
						+ "e Maison\", \n  \"public\".\"adventurework\".\"Nbr Voitures\", \n  \"public\".\"adventurework\".\"Annee\", \n  \"public\"."
						+ "\"adventurework\".\"Mois\", \n  \"public\".\"adventurework\".\"Semaine\", \n  \"public\".\"adventurework\".\"Saison\", \n  "
						+ "\"public\".\"adventurework\".\"Age\", \n  \"public\".\"adventurework\".\"Tranche d'âge\"\nFROM \"public\".\"adventurework"
						+ "\"";

				globalMap.put("tDBInput_1_QUERY", dbquery_tDBInput_1);
				java.sql.ResultSet rs_tDBInput_1 = null;

				try {
					rs_tDBInput_1 = stmt_tDBInput_1.executeQuery(dbquery_tDBInput_1);
					java.sql.ResultSetMetaData rsmd_tDBInput_1 = rs_tDBInput_1.getMetaData();
					int colQtyInRs_tDBInput_1 = rsmd_tDBInput_1.getColumnCount();

					String tmpContent_tDBInput_1 = null;

					while (rs_tDBInput_1.next()) {
						nb_line_tDBInput_1++;

						if (colQtyInRs_tDBInput_1 < 1) {
							row2.ID = 0;
						} else {

							row2.ID = rs_tDBInput_1.getInt(1);
							if (rs_tDBInput_1.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_1 < 2) {
							row2.Quantite_Commande = null;
						} else {

							row2.Quantite_Commande = rs_tDBInput_1.getInt(2);
							if (rs_tDBInput_1.wasNull()) {
								row2.Quantite_Commande = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 3) {
							row2.Cout_standard = null;
						} else {

							row2.Cout_standard = rs_tDBInput_1.getFloat(3);
							if (rs_tDBInput_1.wasNull()) {
								row2.Cout_standard = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 4) {
							row2.Nom_Client = null;
						} else {

							row2.Nom_Client = routines.system.JDBCUtil.getString(rs_tDBInput_1, 4, false);
						}
						if (colQtyInRs_tDBInput_1 < 5) {
							row2.Ville = null;
						} else {

							row2.Ville = routines.system.JDBCUtil.getString(rs_tDBInput_1, 5, false);
						}
						if (colQtyInRs_tDBInput_1 < 6) {
							row2.Nom_Produit = null;
						} else {

							row2.Nom_Produit = routines.system.JDBCUtil.getString(rs_tDBInput_1, 6, false);
						}
						if (colQtyInRs_tDBInput_1 < 7) {
							row2.Couleur = null;
						} else {

							row2.Couleur = routines.system.JDBCUtil.getString(rs_tDBInput_1, 7, false);
						}
						if (colQtyInRs_tDBInput_1 < 8) {
							row2.Prix = null;
						} else {

							row2.Prix = rs_tDBInput_1.getFloat(8);
							if (rs_tDBInput_1.wasNull()) {
								row2.Prix = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 9) {
							row2.Modele = null;
						} else {

							row2.Modele = routines.system.JDBCUtil.getString(rs_tDBInput_1, 9, false);
						}
						if (colQtyInRs_tDBInput_1 < 10) {
							row2.Sous_Categorie = null;
						} else {

							row2.Sous_Categorie = routines.system.JDBCUtil.getString(rs_tDBInput_1, 10, false);
						}
						if (colQtyInRs_tDBInput_1 < 11) {
							row2.Categorie = null;
						} else {

							row2.Categorie = routines.system.JDBCUtil.getString(rs_tDBInput_1, 11, false);
						}
						if (colQtyInRs_tDBInput_1 < 12) {
							row2.Type_Business = null;
						} else {

							row2.Type_Business = routines.system.JDBCUtil.getString(rs_tDBInput_1, 12, false);
						}
						if (colQtyInRs_tDBInput_1 < 13) {
							row2.Type_Canal = null;
						} else {

							row2.Type_Canal = routines.system.JDBCUtil.getString(rs_tDBInput_1, 13, false);
						}
						if (colQtyInRs_tDBInput_1 < 14) {
							row2.Region = null;
						} else {

							row2.Region = routines.system.JDBCUtil.getString(rs_tDBInput_1, 14, false);
						}
						if (colQtyInRs_tDBInput_1 < 15) {
							row2.Pays = null;
						} else {

							row2.Pays = routines.system.JDBCUtil.getString(rs_tDBInput_1, 15, false);
						}
						if (colQtyInRs_tDBInput_1 < 16) {
							row2.Continent = null;
						} else {

							row2.Continent = routines.system.JDBCUtil.getString(rs_tDBInput_1, 16, false);
						}
						if (colQtyInRs_tDBInput_1 < 17) {
							row2.Date = null;
						} else {

							row2.Date = routines.system.JDBCUtil.getDate(rs_tDBInput_1, 17);
						}
						if (colQtyInRs_tDBInput_1 < 18) {
							row2.Etat_Civil = null;
						} else {

							row2.Etat_Civil = routines.system.JDBCUtil.getString(rs_tDBInput_1, 18, false);
						}
						if (colQtyInRs_tDBInput_1 < 19) {
							row2.Genre = null;
						} else {

							row2.Genre = routines.system.JDBCUtil.getString(rs_tDBInput_1, 19, false);
						}
						if (colQtyInRs_tDBInput_1 < 20) {
							row2.Revenu_annuel = null;
						} else {

							row2.Revenu_annuel = rs_tDBInput_1.getFloat(20);
							if (rs_tDBInput_1.wasNull()) {
								row2.Revenu_annuel = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 21) {
							row2.Nbr_Enfants = null;
						} else {

							row2.Nbr_Enfants = rs_tDBInput_1.getInt(21);
							if (rs_tDBInput_1.wasNull()) {
								row2.Nbr_Enfants = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 22) {
							row2.Poste = null;
						} else {

							row2.Poste = routines.system.JDBCUtil.getString(rs_tDBInput_1, 22, false);
						}
						if (colQtyInRs_tDBInput_1 < 23) {
							row2.Propeietaire_Maison = null;
						} else {

							row2.Propeietaire_Maison = routines.system.JDBCUtil.getString(rs_tDBInput_1, 23, false);
						}
						if (colQtyInRs_tDBInput_1 < 24) {
							row2.Nbr_Voitures = null;
						} else {

							row2.Nbr_Voitures = rs_tDBInput_1.getInt(24);
							if (rs_tDBInput_1.wasNull()) {
								row2.Nbr_Voitures = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 25) {
							row2.Annee = null;
						} else {

							row2.Annee = routines.system.JDBCUtil.getString(rs_tDBInput_1, 25, false);
						}
						if (colQtyInRs_tDBInput_1 < 26) {
							row2.Mois = null;
						} else {

							row2.Mois = routines.system.JDBCUtil.getString(rs_tDBInput_1, 26, false);
						}
						if (colQtyInRs_tDBInput_1 < 27) {
							row2.Semaine = null;
						} else {

							row2.Semaine = routines.system.JDBCUtil.getString(rs_tDBInput_1, 27, false);
						}
						if (colQtyInRs_tDBInput_1 < 28) {
							row2.Saison = null;
						} else {

							row2.Saison = routines.system.JDBCUtil.getString(rs_tDBInput_1, 28, false);
						}
						if (colQtyInRs_tDBInput_1 < 29) {
							row2.Age = null;
						} else {

							row2.Age = rs_tDBInput_1.getInt(29);
							if (rs_tDBInput_1.wasNull()) {
								row2.Age = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 30) {
							row2.Tranche_d_age = null;
						} else {

							row2.Tranche_d_age = routines.system.JDBCUtil.getString(rs_tDBInput_1, 30, false);
						}

						/**
						 * [tDBInput_1 begin ] stop
						 */

						/**
						 * [tDBInput_1 main ] start
						 */

						currentComponent = "tDBInput_1";

						tos_count_tDBInput_1++;

						/**
						 * [tDBInput_1 main ] stop
						 */

						/**
						 * [tDBInput_1 process_data_begin ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_begin ] stop
						 */

						/**
						 * [tUnite_1 main ] start
						 */

						currentComponent = "tUnite_1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row2"

							);
						}

//////////

// for output
						row5 = new row5Struct();

						row5.ID = row2.ID;
						row5.Quantite_Commande = row2.Quantite_Commande;
						row5.Cout_standard = row2.Cout_standard;
						row5.Nom_Client = row2.Nom_Client;
						row5.Ville = row2.Ville;
						row5.Nom_Produit = row2.Nom_Produit;
						row5.Couleur = row2.Couleur;
						row5.Prix = row2.Prix;
						row5.Modele = row2.Modele;
						row5.Sous_Categorie = row2.Sous_Categorie;
						row5.Categorie = row2.Categorie;
						row5.Type_Business = row2.Type_Business;
						row5.Type_Canal = row2.Type_Canal;
						row5.Region = row2.Region;
						row5.Pays = row2.Pays;
						row5.Continent = row2.Continent;
						row5.Date = row2.Date;
						row5.Etat_Civil = row2.Etat_Civil;
						row5.Genre = row2.Genre;
						row5.Revenu_annuel = row2.Revenu_annuel;
						row5.Nbr_Enfants = row2.Nbr_Enfants;
						row5.Poste = row2.Poste;
						row5.Propeietaire_Maison = row2.Propeietaire_Maison;
						row5.Nbr_Voitures = row2.Nbr_Voitures;
						row5.Annee = row2.Annee;
						row5.Mois = row2.Mois;
						row5.Semaine = row2.Semaine;
						row5.Saison = row2.Saison;
						row5.Age = row2.Age;
						row5.Tranche_d_age = row2.Tranche_d_age;

						nb_line_tUnite_1++;

//////////

						tos_count_tUnite_1++;

						/**
						 * [tUnite_1 main ] stop
						 */

						/**
						 * [tUnite_1 process_data_begin ] start
						 */

						currentComponent = "tUnite_1";

						/**
						 * [tUnite_1 process_data_begin ] stop
						 */

						/**
						 * [tDBOutput_1 main ] start
						 */

						currentComponent = "tDBOutput_1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row5"

							);
						}

						whetherReject_tDBOutput_1 = false;
						pstmt_tDBOutput_1.setInt(1, row5.ID);

						int checkCount_tDBOutput_1 = -1;
						try (java.sql.ResultSet rs_tDBOutput_1 = pstmt_tDBOutput_1.executeQuery()) {
							while (rs_tDBOutput_1.next()) {
								checkCount_tDBOutput_1 = rs_tDBOutput_1.getInt(1);
							}
						}
						if (checkCount_tDBOutput_1 > 0) {
							if (row5.Quantite_Commande == null) {
								pstmtUpdate_tDBOutput_1.setNull(1, java.sql.Types.INTEGER);
							} else {
								pstmtUpdate_tDBOutput_1.setInt(1, row5.Quantite_Commande);
							}

							if (row5.Cout_standard == null) {
								pstmtUpdate_tDBOutput_1.setNull(2, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_1.setFloat(2, row5.Cout_standard);
							}

							if (row5.Nom_Client == null) {
								pstmtUpdate_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(3, row5.Nom_Client);
							}

							if (row5.Ville == null) {
								pstmtUpdate_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(4, row5.Ville);
							}

							if (row5.Nom_Produit == null) {
								pstmtUpdate_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(5, row5.Nom_Produit);
							}

							if (row5.Couleur == null) {
								pstmtUpdate_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(6, row5.Couleur);
							}

							if (row5.Prix == null) {
								pstmtUpdate_tDBOutput_1.setNull(7, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_1.setFloat(7, row5.Prix);
							}

							if (row5.Modele == null) {
								pstmtUpdate_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(8, row5.Modele);
							}

							if (row5.Sous_Categorie == null) {
								pstmtUpdate_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(9, row5.Sous_Categorie);
							}

							if (row5.Categorie == null) {
								pstmtUpdate_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(10, row5.Categorie);
							}

							if (row5.Type_Business == null) {
								pstmtUpdate_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(11, row5.Type_Business);
							}

							if (row5.Type_Canal == null) {
								pstmtUpdate_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(12, row5.Type_Canal);
							}

							if (row5.Region == null) {
								pstmtUpdate_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(13, row5.Region);
							}

							if (row5.Pays == null) {
								pstmtUpdate_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(14, row5.Pays);
							}

							if (row5.Continent == null) {
								pstmtUpdate_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(15, row5.Continent);
							}

							if (row5.Date != null) {
								pstmtUpdate_tDBOutput_1.setTimestamp(16, new java.sql.Timestamp(row5.Date.getTime()));
							} else {
								pstmtUpdate_tDBOutput_1.setNull(16, java.sql.Types.TIMESTAMP);
							}

							if (row5.Etat_Civil == null) {
								pstmtUpdate_tDBOutput_1.setNull(17, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(17, row5.Etat_Civil);
							}

							if (row5.Genre == null) {
								pstmtUpdate_tDBOutput_1.setNull(18, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(18, row5.Genre);
							}

							if (row5.Revenu_annuel == null) {
								pstmtUpdate_tDBOutput_1.setNull(19, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_1.setFloat(19, row5.Revenu_annuel);
							}

							if (row5.Nbr_Enfants == null) {
								pstmtUpdate_tDBOutput_1.setNull(20, java.sql.Types.INTEGER);
							} else {
								pstmtUpdate_tDBOutput_1.setInt(20, row5.Nbr_Enfants);
							}

							if (row5.Poste == null) {
								pstmtUpdate_tDBOutput_1.setNull(21, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(21, row5.Poste);
							}

							if (row5.Propeietaire_Maison == null) {
								pstmtUpdate_tDBOutput_1.setNull(22, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(22, row5.Propeietaire_Maison);
							}

							if (row5.Nbr_Voitures == null) {
								pstmtUpdate_tDBOutput_1.setNull(23, java.sql.Types.INTEGER);
							} else {
								pstmtUpdate_tDBOutput_1.setInt(23, row5.Nbr_Voitures);
							}

							if (row5.Annee == null) {
								pstmtUpdate_tDBOutput_1.setNull(24, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(24, row5.Annee);
							}

							if (row5.Mois == null) {
								pstmtUpdate_tDBOutput_1.setNull(25, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(25, row5.Mois);
							}

							if (row5.Semaine == null) {
								pstmtUpdate_tDBOutput_1.setNull(26, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(26, row5.Semaine);
							}

							if (row5.Saison == null) {
								pstmtUpdate_tDBOutput_1.setNull(27, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(27, row5.Saison);
							}

							if (row5.Age == null) {
								pstmtUpdate_tDBOutput_1.setNull(28, java.sql.Types.INTEGER);
							} else {
								pstmtUpdate_tDBOutput_1.setInt(28, row5.Age);
							}

							if (row5.Tranche_d_age == null) {
								pstmtUpdate_tDBOutput_1.setNull(29, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(29, row5.Tranche_d_age);
							}

							pstmtUpdate_tDBOutput_1.setInt(30 + count_tDBOutput_1, row5.ID);

							try {

								int processedCount_tDBOutput_1 = pstmtUpdate_tDBOutput_1.executeUpdate();
								updatedCount_tDBOutput_1 += processedCount_tDBOutput_1;
								rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
								nb_line_tDBOutput_1++;

							} catch (java.lang.Exception e) {
								globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

								whetherReject_tDBOutput_1 = true;
								nb_line_tDBOutput_1++;
								System.err.print(e.getMessage());
							}
						} else {
							pstmtInsert_tDBOutput_1.setInt(1, row5.ID);

							if (row5.Quantite_Commande == null) {
								pstmtInsert_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
							} else {
								pstmtInsert_tDBOutput_1.setInt(2, row5.Quantite_Commande);
							}

							if (row5.Cout_standard == null) {
								pstmtInsert_tDBOutput_1.setNull(3, java.sql.Types.FLOAT);
							} else {
								pstmtInsert_tDBOutput_1.setFloat(3, row5.Cout_standard);
							}

							if (row5.Nom_Client == null) {
								pstmtInsert_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(4, row5.Nom_Client);
							}

							if (row5.Ville == null) {
								pstmtInsert_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(5, row5.Ville);
							}

							if (row5.Nom_Produit == null) {
								pstmtInsert_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(6, row5.Nom_Produit);
							}

							if (row5.Couleur == null) {
								pstmtInsert_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(7, row5.Couleur);
							}

							if (row5.Prix == null) {
								pstmtInsert_tDBOutput_1.setNull(8, java.sql.Types.FLOAT);
							} else {
								pstmtInsert_tDBOutput_1.setFloat(8, row5.Prix);
							}

							if (row5.Modele == null) {
								pstmtInsert_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(9, row5.Modele);
							}

							if (row5.Sous_Categorie == null) {
								pstmtInsert_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(10, row5.Sous_Categorie);
							}

							if (row5.Categorie == null) {
								pstmtInsert_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(11, row5.Categorie);
							}

							if (row5.Type_Business == null) {
								pstmtInsert_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(12, row5.Type_Business);
							}

							if (row5.Type_Canal == null) {
								pstmtInsert_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(13, row5.Type_Canal);
							}

							if (row5.Region == null) {
								pstmtInsert_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(14, row5.Region);
							}

							if (row5.Pays == null) {
								pstmtInsert_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(15, row5.Pays);
							}

							if (row5.Continent == null) {
								pstmtInsert_tDBOutput_1.setNull(16, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(16, row5.Continent);
							}

							if (row5.Date != null) {
								pstmtInsert_tDBOutput_1.setTimestamp(17, new java.sql.Timestamp(row5.Date.getTime()));
							} else {
								pstmtInsert_tDBOutput_1.setNull(17, java.sql.Types.TIMESTAMP);
							}

							if (row5.Etat_Civil == null) {
								pstmtInsert_tDBOutput_1.setNull(18, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(18, row5.Etat_Civil);
							}

							if (row5.Genre == null) {
								pstmtInsert_tDBOutput_1.setNull(19, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(19, row5.Genre);
							}

							if (row5.Revenu_annuel == null) {
								pstmtInsert_tDBOutput_1.setNull(20, java.sql.Types.FLOAT);
							} else {
								pstmtInsert_tDBOutput_1.setFloat(20, row5.Revenu_annuel);
							}

							if (row5.Nbr_Enfants == null) {
								pstmtInsert_tDBOutput_1.setNull(21, java.sql.Types.INTEGER);
							} else {
								pstmtInsert_tDBOutput_1.setInt(21, row5.Nbr_Enfants);
							}

							if (row5.Poste == null) {
								pstmtInsert_tDBOutput_1.setNull(22, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(22, row5.Poste);
							}

							if (row5.Propeietaire_Maison == null) {
								pstmtInsert_tDBOutput_1.setNull(23, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(23, row5.Propeietaire_Maison);
							}

							if (row5.Nbr_Voitures == null) {
								pstmtInsert_tDBOutput_1.setNull(24, java.sql.Types.INTEGER);
							} else {
								pstmtInsert_tDBOutput_1.setInt(24, row5.Nbr_Voitures);
							}

							if (row5.Annee == null) {
								pstmtInsert_tDBOutput_1.setNull(25, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(25, row5.Annee);
							}

							if (row5.Mois == null) {
								pstmtInsert_tDBOutput_1.setNull(26, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(26, row5.Mois);
							}

							if (row5.Semaine == null) {
								pstmtInsert_tDBOutput_1.setNull(27, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(27, row5.Semaine);
							}

							if (row5.Saison == null) {
								pstmtInsert_tDBOutput_1.setNull(28, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(28, row5.Saison);
							}

							if (row5.Age == null) {
								pstmtInsert_tDBOutput_1.setNull(29, java.sql.Types.INTEGER);
							} else {
								pstmtInsert_tDBOutput_1.setInt(29, row5.Age);
							}

							if (row5.Tranche_d_age == null) {
								pstmtInsert_tDBOutput_1.setNull(30, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(30, row5.Tranche_d_age);
							}

							try {

								int processedCount_tDBOutput_1 = pstmtInsert_tDBOutput_1.executeUpdate();
								insertedCount_tDBOutput_1 += processedCount_tDBOutput_1;
								rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
								nb_line_tDBOutput_1++;

							} catch (java.lang.Exception e) {
								globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

								whetherReject_tDBOutput_1 = true;
								nb_line_tDBOutput_1++;
								System.err.print(e.getMessage());
							}
						}
						commitCounter_tDBOutput_1++;
						if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {
							if (rowsToCommitCount_tDBOutput_1 != 0) {

							}
							conn_tDBOutput_1.commit();
							if (rowsToCommitCount_tDBOutput_1 != 0) {

								rowsToCommitCount_tDBOutput_1 = 0;
							}
							commitCounter_tDBOutput_1 = 0;
						}

						tos_count_tDBOutput_1++;

						/**
						 * [tDBOutput_1 main ] stop
						 */

						/**
						 * [tDBOutput_1 process_data_begin ] start
						 */

						currentComponent = "tDBOutput_1";

						/**
						 * [tDBOutput_1 process_data_begin ] stop
						 */

						/**
						 * [tDBOutput_1 process_data_end ] start
						 */

						currentComponent = "tDBOutput_1";

						/**
						 * [tDBOutput_1 process_data_end ] stop
						 */

						/**
						 * [tUnite_1 process_data_end ] start
						 */

						currentComponent = "tUnite_1";

						/**
						 * [tUnite_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 process_data_end ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 end ] start
						 */

						currentComponent = "tDBInput_1";

					}
				} finally {
					if (rs_tDBInput_1 != null) {
						rs_tDBInput_1.close();
					}
					if (stmt_tDBInput_1 != null) {
						stmt_tDBInput_1.close();
					}
					if (conn_tDBInput_1 != null && !conn_tDBInput_1.isClosed()) {

						conn_tDBInput_1.commit();

						conn_tDBInput_1.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}

				}
				globalMap.put("tDBInput_1_NB_LINE", nb_line_tDBInput_1);

				ok_Hash.put("tDBInput_1", true);
				end_Hash.put("tDBInput_1", System.currentTimeMillis());

				/**
				 * [tDBInput_1 end ] stop
				 */

				/**
				 * [tFileInputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_1", false);
				start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_1";

				int tos_count_tFileInputExcel_1 = 0;

				final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:wo6LiuRzunl/MZm/gQId9g6p/cWB1ngLjkOuMA==");
				String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
				if (password_tFileInputExcel_1.isEmpty()) {
					password_tFileInputExcel_1 = null;
				}
				class RegexUtil_tFileInputExcel_1 {

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
							boolean useRegex) {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

						if (useRegex) {// this part process the regex issue

							java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
							for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
								String sheetName = sheet.getSheetName();
								java.util.regex.Matcher matcher = pattern.matcher(sheetName);
								if (matcher.matches()) {
									if (sheet != null) {
										list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
									}
								}
							}

						} else {
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheet(oneSheetName);
							if (sheet != null) {
								list.add(sheet);
							}

						}

						return list;
					}

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
								.getSheetAt(index);
						if (sheet != null) {
							list.add(sheet);
						}
						return list;
					}

				}
				RegexUtil_tFileInputExcel_1 regexUtil_tFileInputExcel_1 = new RegexUtil_tFileInputExcel_1();

				Object source_tFileInputExcel_1 = "C:/Users/hamza/OneDrive/Desktop/Data/bi_adventurework/Data/Adventure_works2020.xlsx";
				org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_1 = null;

				if (source_tFileInputExcel_1 instanceof String) {
					workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create(new java.io.File((String) source_tFileInputExcel_1), password_tFileInputExcel_1,
									true);
				} else if (source_tFileInputExcel_1 instanceof java.io.InputStream) {
					workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create((java.io.InputStream) source_tFileInputExcel_1, password_tFileInputExcel_1);
				} else {
					workbook_tFileInputExcel_1 = null;
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}
				try {

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.ss.usermodel.Sheet sheet_tFileInputExcel_1 : workbook_tFileInputExcel_1) {
						sheetList_tFileInputExcel_1
								.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet_tFileInputExcel_1);
					}
					if (sheetList_tFileInputExcel_1.size() <= 0) {
						throw new RuntimeException("Special sheets not exist!");
					}

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
						if (sheet_FilterNull_tFileInputExcel_1 != null
								&& sheetList_FilterNull_tFileInputExcel_1.iterator() != null
								&& sheet_FilterNull_tFileInputExcel_1.iterator().hasNext()) {
							sheetList_FilterNull_tFileInputExcel_1.add(sheet_FilterNull_tFileInputExcel_1);
						}
					}
					sheetList_tFileInputExcel_1 = sheetList_FilterNull_tFileInputExcel_1;
					if (sheetList_tFileInputExcel_1.size() > 0) {
						int nb_line_tFileInputExcel_1 = 0;

						int begin_line_tFileInputExcel_1 = 1;

						int footer_input_tFileInputExcel_1 = 0;

						int end_line_tFileInputExcel_1 = 0;
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
							end_line_tFileInputExcel_1 += (sheet_tFileInputExcel_1.getLastRowNum() + 1);
						}
						end_line_tFileInputExcel_1 -= footer_input_tFileInputExcel_1;
						int limit_tFileInputExcel_1 = -1;
						int start_column_tFileInputExcel_1 = 1 - 1;
						int end_column_tFileInputExcel_1 = -1;

						org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_1 = null;
						org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
								.get(0);
						int rowCount_tFileInputExcel_1 = 0;
						int sheetIndex_tFileInputExcel_1 = 0;
						int currentRows_tFileInputExcel_1 = (sheetList_tFileInputExcel_1.get(0).getLastRowNum() + 1);

						// for the number format
						java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat(
								"#.####################################");
						char decimalChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols()
								.getDecimalSeparator();

						for (int i_tFileInputExcel_1 = begin_line_tFileInputExcel_1; i_tFileInputExcel_1 < end_line_tFileInputExcel_1; i_tFileInputExcel_1++) {

							int emptyColumnCount_tFileInputExcel_1 = 0;

							if (limit_tFileInputExcel_1 != -1 && nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
								break;
							}

							while (i_tFileInputExcel_1 >= rowCount_tFileInputExcel_1 + currentRows_tFileInputExcel_1) {
								rowCount_tFileInputExcel_1 += currentRows_tFileInputExcel_1;
								sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
										.get(++sheetIndex_tFileInputExcel_1);
								currentRows_tFileInputExcel_1 = (sheet_tFileInputExcel_1.getLastRowNum() + 1);
							}
							globalMap.put("tFileInputExcel_1_CURRENT_SHEET", sheet_tFileInputExcel_1.getSheetName());
							if (rowCount_tFileInputExcel_1 <= i_tFileInputExcel_1) {
								row_tFileInputExcel_1 = sheet_tFileInputExcel_1
										.getRow(i_tFileInputExcel_1 - rowCount_tFileInputExcel_1);
							}
							row4 = null;
							int tempRowLength_tFileInputExcel_1 = 30;

							int columnIndex_tFileInputExcel_1 = 0;

							String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];
							int excel_end_column_tFileInputExcel_1;
							if (row_tFileInputExcel_1 == null) {
								excel_end_column_tFileInputExcel_1 = 0;
							} else {
								excel_end_column_tFileInputExcel_1 = row_tFileInputExcel_1.getLastCellNum();
							}
							int actual_end_column_tFileInputExcel_1;
							if (end_column_tFileInputExcel_1 == -1) {
								actual_end_column_tFileInputExcel_1 = excel_end_column_tFileInputExcel_1;
							} else {
								actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 > excel_end_column_tFileInputExcel_1
										? excel_end_column_tFileInputExcel_1
										: end_column_tFileInputExcel_1;
							}
							org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_1 = null;
							for (int i = 0; i < tempRowLength_tFileInputExcel_1; i++) {
								if (i + start_column_tFileInputExcel_1 < actual_end_column_tFileInputExcel_1) {
									org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_1 = row_tFileInputExcel_1
											.getCell(i + start_column_tFileInputExcel_1);
									if (cell_tFileInputExcel_1 != null) {
										switch (cell_tFileInputExcel_1.getCellType()) {
										case STRING:
											temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
													.getRichStringCellValue().getString();
											break;
										case NUMERIC:
											if (org.apache.poi.ss.usermodel.DateUtil
													.isCellDateFormatted(cell_tFileInputExcel_1)) {
												temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
														.getDateCellValue().toString();
											} else {
												temp_row_tFileInputExcel_1[i] = df_tFileInputExcel_1
														.format(cell_tFileInputExcel_1.getNumericCellValue());
											}
											break;
										case BOOLEAN:
											temp_row_tFileInputExcel_1[i] = String
													.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
											break;
										case FORMULA:
											switch (cell_tFileInputExcel_1.getCachedFormulaResultType()) {
											case STRING:
												temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_1)) {
													temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
															.getDateCellValue().toString();
												} else {
													ne_tFileInputExcel_1 = new org.apache.poi.ss.formula.eval.NumberEval(
															cell_tFileInputExcel_1.getNumericCellValue());
													temp_row_tFileInputExcel_1[i] = ne_tFileInputExcel_1
															.getStringValue();
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_1[i] = String
														.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
												break;
											default:
												temp_row_tFileInputExcel_1[i] = "";
											}
											break;
										default:
											temp_row_tFileInputExcel_1[i] = "";
										}
									} else {
										temp_row_tFileInputExcel_1[i] = "";
									}

								} else {
									temp_row_tFileInputExcel_1[i] = "";
								}
							}
							boolean whetherReject_tFileInputExcel_1 = false;
							row4 = new row4Struct();
							int curColNum_tFileInputExcel_1 = -1;
							String curColName_tFileInputExcel_1 = "";
							try {
								columnIndex_tFileInputExcel_1 = 0;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "ID";

									row4.ID = ParserUtils.parseTo_int(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row4.ID = 0;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 1;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Quantite_Commande";

									row4.Quantite_Commande = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row4.Quantite_Commande = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 2;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Cout_standard";

									row4.Cout_standard = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row4.Cout_standard = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 3;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom_Client";

									row4.Nom_Client = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Nom_Client = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 4;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Ville";

									row4.Ville = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Ville = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 5;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom_Produit";

									row4.Nom_Produit = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Nom_Produit = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 6;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Couleur";

									row4.Couleur = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Couleur = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 7;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Prix";

									row4.Prix = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row4.Prix = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 8;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Modele";

									row4.Modele = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Modele = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 9;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sous_Categorie";

									row4.Sous_Categorie = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Sous_Categorie = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 10;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Categorie";

									row4.Categorie = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Categorie = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 11;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Type_Business";

									row4.Type_Business = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Type_Business = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 12;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Type_Canal";

									row4.Type_Canal = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Type_Canal = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 13;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Region";

									row4.Region = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Region = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 14;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Pays";

									row4.Pays = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Pays = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 15;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Continent";

									row4.Continent = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Continent = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 16;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Date";

									if (16 < actual_end_column_tFileInputExcel_1) {
										try {
											if (row_tFileInputExcel_1
													.getCell(columnIndex_tFileInputExcel_1
															+ start_column_tFileInputExcel_1)
													.getCellType() == org.apache.poi.ss.usermodel.CellType.NUMERIC
													&& org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(
															row_tFileInputExcel_1.getCell(columnIndex_tFileInputExcel_1
																	+ start_column_tFileInputExcel_1))) {
												row4.Date = row_tFileInputExcel_1.getCell(
														columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1)
														.getDateCellValue();
											} else {
												java.util.Date tempDate_tFileInputExcel_1 = ParserUtils.parseTo_Date(
														temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1],
														"yyyy-MM-dd");
												if (tempDate_tFileInputExcel_1
														.after((new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS"))
																.parse("9999/12/31 23:59:59.999"))
														|| tempDate_tFileInputExcel_1
																.before((new SimpleDateFormat("yyyy/MM/dd"))
																		.parse("1900/01/01"))) {
													throw new RuntimeException("The cell format is not Date in ( Row. "
															+ (nb_line_tFileInputExcel_1 + 1) + " and ColumnNum. "
															+ curColNum_tFileInputExcel_1 + " )");
												} else {
													row4.Date = tempDate_tFileInputExcel_1;
												}
											}
										} catch (java.lang.Exception e) {
											globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());

											throw new RuntimeException("The cell format is not Date in ( Row. "
													+ (nb_line_tFileInputExcel_1 + 1) + " and ColumnNum. "
													+ curColNum_tFileInputExcel_1 + " )");
										}
									}

								} else {
									row4.Date = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 17;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Etat_Civil";

									row4.Etat_Civil = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Etat_Civil = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 18;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Genre";

									row4.Genre = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Genre = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 19;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Revenu_annuel";

									row4.Revenu_annuel = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row4.Revenu_annuel = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 20;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nbr_Enfants";

									row4.Nbr_Enfants = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row4.Nbr_Enfants = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 21;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Poste";

									row4.Poste = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Poste = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 22;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Propeietaire_Maison";

									row4.Propeietaire_Maison = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Propeietaire_Maison = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 23;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nbr_Voitures";

									row4.Nbr_Voitures = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row4.Nbr_Voitures = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 24;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Annee";

									row4.Annee = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Annee = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 25;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Mois";

									row4.Mois = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Mois = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 26;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Semaine";

									row4.Semaine = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Semaine = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 27;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Saison";

									row4.Saison = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Saison = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 28;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Age";

									row4.Age = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row4.Age = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 29;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Tranche_d_age";

									row4.Tranche_d_age = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row4.Tranche_d_age = null;
									emptyColumnCount_tFileInputExcel_1++;
								}

								nb_line_tFileInputExcel_1++;

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_1 = true;
								System.err.println(e.getMessage());
								row4 = null;
							}

							/**
							 * [tFileInputExcel_1 begin ] stop
							 */

							/**
							 * [tFileInputExcel_1 main ] start
							 */

							currentComponent = "tFileInputExcel_1";

							tos_count_tFileInputExcel_1++;

							/**
							 * [tFileInputExcel_1 main ] stop
							 */

							/**
							 * [tFileInputExcel_1 process_data_begin ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_begin ] stop
							 */
// Start of branch "row4"
							if (row4 != null) {

								/**
								 * [tUnite_1 main ] start
								 */

								currentComponent = "tUnite_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row4"

									);
								}

//////////

// for output
								row5 = new row5Struct();

								row5.ID = row4.ID;
								row5.Quantite_Commande = row4.Quantite_Commande;
								row5.Cout_standard = row4.Cout_standard;
								row5.Nom_Client = row4.Nom_Client;
								row5.Ville = row4.Ville;
								row5.Nom_Produit = row4.Nom_Produit;
								row5.Couleur = row4.Couleur;
								row5.Prix = row4.Prix;
								row5.Modele = row4.Modele;
								row5.Sous_Categorie = row4.Sous_Categorie;
								row5.Categorie = row4.Categorie;
								row5.Type_Business = row4.Type_Business;
								row5.Type_Canal = row4.Type_Canal;
								row5.Region = row4.Region;
								row5.Pays = row4.Pays;
								row5.Continent = row4.Continent;
								row5.Date = row4.Date;
								row5.Etat_Civil = row4.Etat_Civil;
								row5.Genre = row4.Genre;
								row5.Revenu_annuel = row4.Revenu_annuel;
								row5.Nbr_Enfants = row4.Nbr_Enfants;
								row5.Poste = row4.Poste;
								row5.Propeietaire_Maison = row4.Propeietaire_Maison;
								row5.Nbr_Voitures = row4.Nbr_Voitures;
								row5.Annee = row4.Annee;
								row5.Mois = row4.Mois;
								row5.Semaine = row4.Semaine;
								row5.Saison = row4.Saison;
								row5.Age = row4.Age;
								row5.Tranche_d_age = row4.Tranche_d_age;

								nb_line_tUnite_1++;

//////////

								tos_count_tUnite_1++;

								/**
								 * [tUnite_1 main ] stop
								 */

								/**
								 * [tUnite_1 process_data_begin ] start
								 */

								currentComponent = "tUnite_1";

								/**
								 * [tUnite_1 process_data_begin ] stop
								 */

								/**
								 * [tDBOutput_1 main ] start
								 */

								currentComponent = "tDBOutput_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row5"

									);
								}

								whetherReject_tDBOutput_1 = false;
								pstmt_tDBOutput_1.setInt(1, row5.ID);

								int checkCount_tDBOutput_1 = -1;
								try (java.sql.ResultSet rs_tDBOutput_1 = pstmt_tDBOutput_1.executeQuery()) {
									while (rs_tDBOutput_1.next()) {
										checkCount_tDBOutput_1 = rs_tDBOutput_1.getInt(1);
									}
								}
								if (checkCount_tDBOutput_1 > 0) {
									if (row5.Quantite_Commande == null) {
										pstmtUpdate_tDBOutput_1.setNull(1, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(1, row5.Quantite_Commande);
									}

									if (row5.Cout_standard == null) {
										pstmtUpdate_tDBOutput_1.setNull(2, java.sql.Types.FLOAT);
									} else {
										pstmtUpdate_tDBOutput_1.setFloat(2, row5.Cout_standard);
									}

									if (row5.Nom_Client == null) {
										pstmtUpdate_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(3, row5.Nom_Client);
									}

									if (row5.Ville == null) {
										pstmtUpdate_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(4, row5.Ville);
									}

									if (row5.Nom_Produit == null) {
										pstmtUpdate_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(5, row5.Nom_Produit);
									}

									if (row5.Couleur == null) {
										pstmtUpdate_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(6, row5.Couleur);
									}

									if (row5.Prix == null) {
										pstmtUpdate_tDBOutput_1.setNull(7, java.sql.Types.FLOAT);
									} else {
										pstmtUpdate_tDBOutput_1.setFloat(7, row5.Prix);
									}

									if (row5.Modele == null) {
										pstmtUpdate_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(8, row5.Modele);
									}

									if (row5.Sous_Categorie == null) {
										pstmtUpdate_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(9, row5.Sous_Categorie);
									}

									if (row5.Categorie == null) {
										pstmtUpdate_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(10, row5.Categorie);
									}

									if (row5.Type_Business == null) {
										pstmtUpdate_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(11, row5.Type_Business);
									}

									if (row5.Type_Canal == null) {
										pstmtUpdate_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(12, row5.Type_Canal);
									}

									if (row5.Region == null) {
										pstmtUpdate_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(13, row5.Region);
									}

									if (row5.Pays == null) {
										pstmtUpdate_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(14, row5.Pays);
									}

									if (row5.Continent == null) {
										pstmtUpdate_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(15, row5.Continent);
									}

									if (row5.Date != null) {
										pstmtUpdate_tDBOutput_1.setTimestamp(16,
												new java.sql.Timestamp(row5.Date.getTime()));
									} else {
										pstmtUpdate_tDBOutput_1.setNull(16, java.sql.Types.TIMESTAMP);
									}

									if (row5.Etat_Civil == null) {
										pstmtUpdate_tDBOutput_1.setNull(17, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(17, row5.Etat_Civil);
									}

									if (row5.Genre == null) {
										pstmtUpdate_tDBOutput_1.setNull(18, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(18, row5.Genre);
									}

									if (row5.Revenu_annuel == null) {
										pstmtUpdate_tDBOutput_1.setNull(19, java.sql.Types.FLOAT);
									} else {
										pstmtUpdate_tDBOutput_1.setFloat(19, row5.Revenu_annuel);
									}

									if (row5.Nbr_Enfants == null) {
										pstmtUpdate_tDBOutput_1.setNull(20, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(20, row5.Nbr_Enfants);
									}

									if (row5.Poste == null) {
										pstmtUpdate_tDBOutput_1.setNull(21, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(21, row5.Poste);
									}

									if (row5.Propeietaire_Maison == null) {
										pstmtUpdate_tDBOutput_1.setNull(22, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(22, row5.Propeietaire_Maison);
									}

									if (row5.Nbr_Voitures == null) {
										pstmtUpdate_tDBOutput_1.setNull(23, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(23, row5.Nbr_Voitures);
									}

									if (row5.Annee == null) {
										pstmtUpdate_tDBOutput_1.setNull(24, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(24, row5.Annee);
									}

									if (row5.Mois == null) {
										pstmtUpdate_tDBOutput_1.setNull(25, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(25, row5.Mois);
									}

									if (row5.Semaine == null) {
										pstmtUpdate_tDBOutput_1.setNull(26, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(26, row5.Semaine);
									}

									if (row5.Saison == null) {
										pstmtUpdate_tDBOutput_1.setNull(27, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(27, row5.Saison);
									}

									if (row5.Age == null) {
										pstmtUpdate_tDBOutput_1.setNull(28, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(28, row5.Age);
									}

									if (row5.Tranche_d_age == null) {
										pstmtUpdate_tDBOutput_1.setNull(29, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(29, row5.Tranche_d_age);
									}

									pstmtUpdate_tDBOutput_1.setInt(30 + count_tDBOutput_1, row5.ID);

									try {

										int processedCount_tDBOutput_1 = pstmtUpdate_tDBOutput_1.executeUpdate();
										updatedCount_tDBOutput_1 += processedCount_tDBOutput_1;
										rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
										nb_line_tDBOutput_1++;

									} catch (java.lang.Exception e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

										whetherReject_tDBOutput_1 = true;
										nb_line_tDBOutput_1++;
										System.err.print(e.getMessage());
									}
								} else {
									pstmtInsert_tDBOutput_1.setInt(1, row5.ID);

									if (row5.Quantite_Commande == null) {
										pstmtInsert_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(2, row5.Quantite_Commande);
									}

									if (row5.Cout_standard == null) {
										pstmtInsert_tDBOutput_1.setNull(3, java.sql.Types.FLOAT);
									} else {
										pstmtInsert_tDBOutput_1.setFloat(3, row5.Cout_standard);
									}

									if (row5.Nom_Client == null) {
										pstmtInsert_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(4, row5.Nom_Client);
									}

									if (row5.Ville == null) {
										pstmtInsert_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(5, row5.Ville);
									}

									if (row5.Nom_Produit == null) {
										pstmtInsert_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(6, row5.Nom_Produit);
									}

									if (row5.Couleur == null) {
										pstmtInsert_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(7, row5.Couleur);
									}

									if (row5.Prix == null) {
										pstmtInsert_tDBOutput_1.setNull(8, java.sql.Types.FLOAT);
									} else {
										pstmtInsert_tDBOutput_1.setFloat(8, row5.Prix);
									}

									if (row5.Modele == null) {
										pstmtInsert_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(9, row5.Modele);
									}

									if (row5.Sous_Categorie == null) {
										pstmtInsert_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(10, row5.Sous_Categorie);
									}

									if (row5.Categorie == null) {
										pstmtInsert_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(11, row5.Categorie);
									}

									if (row5.Type_Business == null) {
										pstmtInsert_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(12, row5.Type_Business);
									}

									if (row5.Type_Canal == null) {
										pstmtInsert_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(13, row5.Type_Canal);
									}

									if (row5.Region == null) {
										pstmtInsert_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(14, row5.Region);
									}

									if (row5.Pays == null) {
										pstmtInsert_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(15, row5.Pays);
									}

									if (row5.Continent == null) {
										pstmtInsert_tDBOutput_1.setNull(16, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(16, row5.Continent);
									}

									if (row5.Date != null) {
										pstmtInsert_tDBOutput_1.setTimestamp(17,
												new java.sql.Timestamp(row5.Date.getTime()));
									} else {
										pstmtInsert_tDBOutput_1.setNull(17, java.sql.Types.TIMESTAMP);
									}

									if (row5.Etat_Civil == null) {
										pstmtInsert_tDBOutput_1.setNull(18, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(18, row5.Etat_Civil);
									}

									if (row5.Genre == null) {
										pstmtInsert_tDBOutput_1.setNull(19, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(19, row5.Genre);
									}

									if (row5.Revenu_annuel == null) {
										pstmtInsert_tDBOutput_1.setNull(20, java.sql.Types.FLOAT);
									} else {
										pstmtInsert_tDBOutput_1.setFloat(20, row5.Revenu_annuel);
									}

									if (row5.Nbr_Enfants == null) {
										pstmtInsert_tDBOutput_1.setNull(21, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(21, row5.Nbr_Enfants);
									}

									if (row5.Poste == null) {
										pstmtInsert_tDBOutput_1.setNull(22, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(22, row5.Poste);
									}

									if (row5.Propeietaire_Maison == null) {
										pstmtInsert_tDBOutput_1.setNull(23, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(23, row5.Propeietaire_Maison);
									}

									if (row5.Nbr_Voitures == null) {
										pstmtInsert_tDBOutput_1.setNull(24, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(24, row5.Nbr_Voitures);
									}

									if (row5.Annee == null) {
										pstmtInsert_tDBOutput_1.setNull(25, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(25, row5.Annee);
									}

									if (row5.Mois == null) {
										pstmtInsert_tDBOutput_1.setNull(26, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(26, row5.Mois);
									}

									if (row5.Semaine == null) {
										pstmtInsert_tDBOutput_1.setNull(27, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(27, row5.Semaine);
									}

									if (row5.Saison == null) {
										pstmtInsert_tDBOutput_1.setNull(28, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(28, row5.Saison);
									}

									if (row5.Age == null) {
										pstmtInsert_tDBOutput_1.setNull(29, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(29, row5.Age);
									}

									if (row5.Tranche_d_age == null) {
										pstmtInsert_tDBOutput_1.setNull(30, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(30, row5.Tranche_d_age);
									}

									try {

										int processedCount_tDBOutput_1 = pstmtInsert_tDBOutput_1.executeUpdate();
										insertedCount_tDBOutput_1 += processedCount_tDBOutput_1;
										rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
										nb_line_tDBOutput_1++;

									} catch (java.lang.Exception e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

										whetherReject_tDBOutput_1 = true;
										nb_line_tDBOutput_1++;
										System.err.print(e.getMessage());
									}
								}
								commitCounter_tDBOutput_1++;
								if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {
									if (rowsToCommitCount_tDBOutput_1 != 0) {

									}
									conn_tDBOutput_1.commit();
									if (rowsToCommitCount_tDBOutput_1 != 0) {

										rowsToCommitCount_tDBOutput_1 = 0;
									}
									commitCounter_tDBOutput_1 = 0;
								}

								tos_count_tDBOutput_1++;

								/**
								 * [tDBOutput_1 main ] stop
								 */

								/**
								 * [tDBOutput_1 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_begin ] stop
								 */

								/**
								 * [tDBOutput_1 process_data_end ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_end ] stop
								 */

								/**
								 * [tUnite_1 process_data_end ] start
								 */

								currentComponent = "tUnite_1";

								/**
								 * [tUnite_1 process_data_end ] stop
								 */

							} // End of branch "row4"

							/**
							 * [tFileInputExcel_1 process_data_end ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_end ] stop
							 */

							/**
							 * [tFileInputExcel_1 end ] start
							 */

							currentComponent = "tFileInputExcel_1";

						}

						globalMap.put("tFileInputExcel_1_NB_LINE", nb_line_tFileInputExcel_1);

					}

				} finally {

					if (!(source_tFileInputExcel_1 instanceof java.io.InputStream)) {
						workbook_tFileInputExcel_1.getPackage().revert();
					}

				}

				ok_Hash.put("tFileInputExcel_1", true);
				end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_1 end ] stop
				 */

				/**
				 * [tDBInput_2 begin ] start
				 */

				ok_Hash.put("tDBInput_2", false);
				start_Hash.put("tDBInput_2", System.currentTimeMillis());

				currentComponent = "tDBInput_2";

				int tos_count_tDBInput_2 = 0;

				java.util.Calendar calendar_tDBInput_2 = java.util.Calendar.getInstance();
				calendar_tDBInput_2.set(0, 0, 0, 0, 0, 0);
				java.util.Date year0_tDBInput_2 = calendar_tDBInput_2.getTime();
				int nb_line_tDBInput_2 = 0;
				java.sql.Connection conn_tDBInput_2 = null;
				String driverClass_tDBInput_2 = "com.mysql.cj.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_2 = java.lang.Class.forName(driverClass_tDBInput_2);
				String dbUser_tDBInput_2 = "root";

				final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:iyJxNuhrByg1yAWPU9rYQ0lHed7I6DEDjxey6w==");

				String dbPwd_tDBInput_2 = decryptedPassword_tDBInput_2;

				String properties_tDBInput_2 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
				if (properties_tDBInput_2 == null || properties_tDBInput_2.trim().length() == 0) {
					properties_tDBInput_2 = "";
				}
				String url_tDBInput_2 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "adventurework" + "?"
						+ properties_tDBInput_2;

				conn_tDBInput_2 = java.sql.DriverManager.getConnection(url_tDBInput_2, dbUser_tDBInput_2,
						dbPwd_tDBInput_2);

				java.sql.Statement stmt_tDBInput_2 = conn_tDBInput_2.createStatement();

				String dbquery_tDBInput_2 = "SELECT \n  `adventurework`.`index`, \n  `adventurework`.`Quantite Commande`, \n  `adventurework`.`Cout standard`, \n  `adve"
						+ "nturework`.`Nom Client`, \n  `adventurework`.`Ville`, \n  `adventurework`.`Nom Produit`, \n  `adventurework`.`Couleur`, \n  "
						+ "`adventurework`.`Prix`, \n  `adventurework`.`Modele`, \n  `adventurework`.`Sous Categorie`, \n  `adventurework`.`Categorie`"
						+ ", \n  `adventurework`.`Type Business`, \n  `adventurework`.`Type Canal`, \n  `adventurework`.`Region`, \n  `adventurework`.`"
						+ "Pays`, \n  `adventurework`.`Continent`, \n  `adventurework`.`Date`, \n  `adventurework`.`Etat Civil`, \n  `adventurework`.`G"
						+ "enre`, \n  `adventurework`.`Revenu annuel`, \n  `adventurework`.`Nbr Enfants`, \n  `adventurework`.`Poste`, \n  `adventurewo"
						+ "rk`.`Propeietaire Maison`, \n  `adventurework`.`Nbr Voitures`, \n  `adventurework`.`Annee`, \n  `adventurework`.`Mois`, \n  "
						+ "`adventurework`.`Semaine`, \n  `adventurework`.`Saison`, \n  `adventurework`.`Age`, \n  `adventurework`.`Tranche d'âge`\nFRO"
						+ "M `adventurework`";

				globalMap.put("tDBInput_2_QUERY", dbquery_tDBInput_2);
				java.sql.ResultSet rs_tDBInput_2 = null;

				try {
					rs_tDBInput_2 = stmt_tDBInput_2.executeQuery(dbquery_tDBInput_2);
					java.sql.ResultSetMetaData rsmd_tDBInput_2 = rs_tDBInput_2.getMetaData();
					int colQtyInRs_tDBInput_2 = rsmd_tDBInput_2.getColumnCount();

					String tmpContent_tDBInput_2 = null;

					while (rs_tDBInput_2.next()) {
						nb_line_tDBInput_2++;

						if (colQtyInRs_tDBInput_2 < 1) {
							row3.ID = 0;
						} else {

							row3.ID = rs_tDBInput_2.getInt(1);
							if (rs_tDBInput_2.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_2 < 2) {
							row3.Quantite_Commande = null;
						} else {

							row3.Quantite_Commande = rs_tDBInput_2.getInt(2);
							if (rs_tDBInput_2.wasNull()) {
								row3.Quantite_Commande = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 3) {
							row3.Cout_standard = null;
						} else {

							row3.Cout_standard = rs_tDBInput_2.getFloat(3);
							if (rs_tDBInput_2.wasNull()) {
								row3.Cout_standard = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 4) {
							row3.Nom_Client = null;
						} else {

							row3.Nom_Client = routines.system.JDBCUtil.getString(rs_tDBInput_2, 4, false);
						}
						if (colQtyInRs_tDBInput_2 < 5) {
							row3.Ville = null;
						} else {

							row3.Ville = routines.system.JDBCUtil.getString(rs_tDBInput_2, 5, false);
						}
						if (colQtyInRs_tDBInput_2 < 6) {
							row3.Nom_Produit = null;
						} else {

							row3.Nom_Produit = routines.system.JDBCUtil.getString(rs_tDBInput_2, 6, false);
						}
						if (colQtyInRs_tDBInput_2 < 7) {
							row3.Couleur = null;
						} else {

							row3.Couleur = routines.system.JDBCUtil.getString(rs_tDBInput_2, 7, false);
						}
						if (colQtyInRs_tDBInput_2 < 8) {
							row3.Prix = null;
						} else {

							row3.Prix = rs_tDBInput_2.getFloat(8);
							if (rs_tDBInput_2.wasNull()) {
								row3.Prix = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 9) {
							row3.Modele = null;
						} else {

							row3.Modele = routines.system.JDBCUtil.getString(rs_tDBInput_2, 9, false);
						}
						if (colQtyInRs_tDBInput_2 < 10) {
							row3.Sous_Categorie = null;
						} else {

							row3.Sous_Categorie = routines.system.JDBCUtil.getString(rs_tDBInput_2, 10, false);
						}
						if (colQtyInRs_tDBInput_2 < 11) {
							row3.Categorie = null;
						} else {

							row3.Categorie = routines.system.JDBCUtil.getString(rs_tDBInput_2, 11, false);
						}
						if (colQtyInRs_tDBInput_2 < 12) {
							row3.Type_Business = null;
						} else {

							row3.Type_Business = routines.system.JDBCUtil.getString(rs_tDBInput_2, 12, false);
						}
						if (colQtyInRs_tDBInput_2 < 13) {
							row3.Type_Canal = null;
						} else {

							row3.Type_Canal = routines.system.JDBCUtil.getString(rs_tDBInput_2, 13, false);
						}
						if (colQtyInRs_tDBInput_2 < 14) {
							row3.Region = null;
						} else {

							row3.Region = routines.system.JDBCUtil.getString(rs_tDBInput_2, 14, false);
						}
						if (colQtyInRs_tDBInput_2 < 15) {
							row3.Pays = null;
						} else {

							row3.Pays = routines.system.JDBCUtil.getString(rs_tDBInput_2, 15, false);
						}
						if (colQtyInRs_tDBInput_2 < 16) {
							row3.Continent = null;
						} else {

							row3.Continent = routines.system.JDBCUtil.getString(rs_tDBInput_2, 16, false);
						}
						if (colQtyInRs_tDBInput_2 < 17) {
							row3.Date = null;
						} else {

							if (rs_tDBInput_2.getString(17) != null) {
								String dateString_tDBInput_2 = rs_tDBInput_2.getString(17);
								if (!("0000-00-00").equals(dateString_tDBInput_2)
										&& !("0000-00-00 00:00:00").equals(dateString_tDBInput_2)) {
									row3.Date = rs_tDBInput_2.getTimestamp(17);
								} else {
									row3.Date = (java.util.Date) year0_tDBInput_2.clone();
								}
							} else {
								row3.Date = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 18) {
							row3.Etat_Civil = null;
						} else {

							row3.Etat_Civil = routines.system.JDBCUtil.getString(rs_tDBInput_2, 18, false);
						}
						if (colQtyInRs_tDBInput_2 < 19) {
							row3.Genre = null;
						} else {

							row3.Genre = routines.system.JDBCUtil.getString(rs_tDBInput_2, 19, false);
						}
						if (colQtyInRs_tDBInput_2 < 20) {
							row3.Revenu_annuel = null;
						} else {

							row3.Revenu_annuel = rs_tDBInput_2.getFloat(20);
							if (rs_tDBInput_2.wasNull()) {
								row3.Revenu_annuel = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 21) {
							row3.Nbr_Enfants = null;
						} else {

							row3.Nbr_Enfants = rs_tDBInput_2.getInt(21);
							if (rs_tDBInput_2.wasNull()) {
								row3.Nbr_Enfants = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 22) {
							row3.Poste = null;
						} else {

							row3.Poste = routines.system.JDBCUtil.getString(rs_tDBInput_2, 22, false);
						}
						if (colQtyInRs_tDBInput_2 < 23) {
							row3.Propeietaire_Maison = null;
						} else {

							row3.Propeietaire_Maison = routines.system.JDBCUtil.getString(rs_tDBInput_2, 23, false);
						}
						if (colQtyInRs_tDBInput_2 < 24) {
							row3.Nbr_Voitures = null;
						} else {

							row3.Nbr_Voitures = rs_tDBInput_2.getInt(24);
							if (rs_tDBInput_2.wasNull()) {
								row3.Nbr_Voitures = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 25) {
							row3.Annee = null;
						} else {

							row3.Annee = routines.system.JDBCUtil.getString(rs_tDBInput_2, 25, false);
						}
						if (colQtyInRs_tDBInput_2 < 26) {
							row3.Mois = null;
						} else {

							row3.Mois = routines.system.JDBCUtil.getString(rs_tDBInput_2, 26, false);
						}
						if (colQtyInRs_tDBInput_2 < 27) {
							row3.Semaine = null;
						} else {

							row3.Semaine = routines.system.JDBCUtil.getString(rs_tDBInput_2, 27, false);
						}
						if (colQtyInRs_tDBInput_2 < 28) {
							row3.Saison = null;
						} else {

							row3.Saison = routines.system.JDBCUtil.getString(rs_tDBInput_2, 28, false);
						}
						if (colQtyInRs_tDBInput_2 < 29) {
							row3.Age = null;
						} else {

							row3.Age = rs_tDBInput_2.getInt(29);
							if (rs_tDBInput_2.wasNull()) {
								row3.Age = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 30) {
							row3.Tranche_d_age = null;
						} else {

							row3.Tranche_d_age = routines.system.JDBCUtil.getString(rs_tDBInput_2, 30, false);
						}

						/**
						 * [tDBInput_2 begin ] stop
						 */

						/**
						 * [tDBInput_2 main ] start
						 */

						currentComponent = "tDBInput_2";

						tos_count_tDBInput_2++;

						/**
						 * [tDBInput_2 main ] stop
						 */

						/**
						 * [tDBInput_2 process_data_begin ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_begin ] stop
						 */

						/**
						 * [tUnite_1 main ] start
						 */

						currentComponent = "tUnite_1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row3"

							);
						}

//////////

// for output
						row5 = new row5Struct();

						row5.ID = row3.ID;
						row5.Quantite_Commande = row3.Quantite_Commande;
						row5.Cout_standard = row3.Cout_standard;
						row5.Nom_Client = row3.Nom_Client;
						row5.Ville = row3.Ville;
						row5.Nom_Produit = row3.Nom_Produit;
						row5.Couleur = row3.Couleur;
						row5.Prix = row3.Prix;
						row5.Modele = row3.Modele;
						row5.Sous_Categorie = row3.Sous_Categorie;
						row5.Categorie = row3.Categorie;
						row5.Type_Business = row3.Type_Business;
						row5.Type_Canal = row3.Type_Canal;
						row5.Region = row3.Region;
						row5.Pays = row3.Pays;
						row5.Continent = row3.Continent;
						row5.Date = row3.Date;
						row5.Etat_Civil = row3.Etat_Civil;
						row5.Genre = row3.Genre;
						row5.Revenu_annuel = row3.Revenu_annuel;
						row5.Nbr_Enfants = row3.Nbr_Enfants;
						row5.Poste = row3.Poste;
						row5.Propeietaire_Maison = row3.Propeietaire_Maison;
						row5.Nbr_Voitures = row3.Nbr_Voitures;
						row5.Annee = row3.Annee;
						row5.Mois = row3.Mois;
						row5.Semaine = row3.Semaine;
						row5.Saison = row3.Saison;
						row5.Age = row3.Age;
						row5.Tranche_d_age = row3.Tranche_d_age;

						nb_line_tUnite_1++;

//////////

						tos_count_tUnite_1++;

						/**
						 * [tUnite_1 main ] stop
						 */

						/**
						 * [tUnite_1 process_data_begin ] start
						 */

						currentComponent = "tUnite_1";

						/**
						 * [tUnite_1 process_data_begin ] stop
						 */

						/**
						 * [tDBOutput_1 main ] start
						 */

						currentComponent = "tDBOutput_1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row5"

							);
						}

						whetherReject_tDBOutput_1 = false;
						pstmt_tDBOutput_1.setInt(1, row5.ID);

						int checkCount_tDBOutput_1 = -1;
						try (java.sql.ResultSet rs_tDBOutput_1 = pstmt_tDBOutput_1.executeQuery()) {
							while (rs_tDBOutput_1.next()) {
								checkCount_tDBOutput_1 = rs_tDBOutput_1.getInt(1);
							}
						}
						if (checkCount_tDBOutput_1 > 0) {
							if (row5.Quantite_Commande == null) {
								pstmtUpdate_tDBOutput_1.setNull(1, java.sql.Types.INTEGER);
							} else {
								pstmtUpdate_tDBOutput_1.setInt(1, row5.Quantite_Commande);
							}

							if (row5.Cout_standard == null) {
								pstmtUpdate_tDBOutput_1.setNull(2, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_1.setFloat(2, row5.Cout_standard);
							}

							if (row5.Nom_Client == null) {
								pstmtUpdate_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(3, row5.Nom_Client);
							}

							if (row5.Ville == null) {
								pstmtUpdate_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(4, row5.Ville);
							}

							if (row5.Nom_Produit == null) {
								pstmtUpdate_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(5, row5.Nom_Produit);
							}

							if (row5.Couleur == null) {
								pstmtUpdate_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(6, row5.Couleur);
							}

							if (row5.Prix == null) {
								pstmtUpdate_tDBOutput_1.setNull(7, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_1.setFloat(7, row5.Prix);
							}

							if (row5.Modele == null) {
								pstmtUpdate_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(8, row5.Modele);
							}

							if (row5.Sous_Categorie == null) {
								pstmtUpdate_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(9, row5.Sous_Categorie);
							}

							if (row5.Categorie == null) {
								pstmtUpdate_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(10, row5.Categorie);
							}

							if (row5.Type_Business == null) {
								pstmtUpdate_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(11, row5.Type_Business);
							}

							if (row5.Type_Canal == null) {
								pstmtUpdate_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(12, row5.Type_Canal);
							}

							if (row5.Region == null) {
								pstmtUpdate_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(13, row5.Region);
							}

							if (row5.Pays == null) {
								pstmtUpdate_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(14, row5.Pays);
							}

							if (row5.Continent == null) {
								pstmtUpdate_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(15, row5.Continent);
							}

							if (row5.Date != null) {
								pstmtUpdate_tDBOutput_1.setTimestamp(16, new java.sql.Timestamp(row5.Date.getTime()));
							} else {
								pstmtUpdate_tDBOutput_1.setNull(16, java.sql.Types.TIMESTAMP);
							}

							if (row5.Etat_Civil == null) {
								pstmtUpdate_tDBOutput_1.setNull(17, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(17, row5.Etat_Civil);
							}

							if (row5.Genre == null) {
								pstmtUpdate_tDBOutput_1.setNull(18, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(18, row5.Genre);
							}

							if (row5.Revenu_annuel == null) {
								pstmtUpdate_tDBOutput_1.setNull(19, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_1.setFloat(19, row5.Revenu_annuel);
							}

							if (row5.Nbr_Enfants == null) {
								pstmtUpdate_tDBOutput_1.setNull(20, java.sql.Types.INTEGER);
							} else {
								pstmtUpdate_tDBOutput_1.setInt(20, row5.Nbr_Enfants);
							}

							if (row5.Poste == null) {
								pstmtUpdate_tDBOutput_1.setNull(21, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(21, row5.Poste);
							}

							if (row5.Propeietaire_Maison == null) {
								pstmtUpdate_tDBOutput_1.setNull(22, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(22, row5.Propeietaire_Maison);
							}

							if (row5.Nbr_Voitures == null) {
								pstmtUpdate_tDBOutput_1.setNull(23, java.sql.Types.INTEGER);
							} else {
								pstmtUpdate_tDBOutput_1.setInt(23, row5.Nbr_Voitures);
							}

							if (row5.Annee == null) {
								pstmtUpdate_tDBOutput_1.setNull(24, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(24, row5.Annee);
							}

							if (row5.Mois == null) {
								pstmtUpdate_tDBOutput_1.setNull(25, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(25, row5.Mois);
							}

							if (row5.Semaine == null) {
								pstmtUpdate_tDBOutput_1.setNull(26, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(26, row5.Semaine);
							}

							if (row5.Saison == null) {
								pstmtUpdate_tDBOutput_1.setNull(27, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(27, row5.Saison);
							}

							if (row5.Age == null) {
								pstmtUpdate_tDBOutput_1.setNull(28, java.sql.Types.INTEGER);
							} else {
								pstmtUpdate_tDBOutput_1.setInt(28, row5.Age);
							}

							if (row5.Tranche_d_age == null) {
								pstmtUpdate_tDBOutput_1.setNull(29, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_1.setString(29, row5.Tranche_d_age);
							}

							pstmtUpdate_tDBOutput_1.setInt(30 + count_tDBOutput_1, row5.ID);

							try {

								int processedCount_tDBOutput_1 = pstmtUpdate_tDBOutput_1.executeUpdate();
								updatedCount_tDBOutput_1 += processedCount_tDBOutput_1;
								rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
								nb_line_tDBOutput_1++;

							} catch (java.lang.Exception e) {
								globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

								whetherReject_tDBOutput_1 = true;
								nb_line_tDBOutput_1++;
								System.err.print(e.getMessage());
							}
						} else {
							pstmtInsert_tDBOutput_1.setInt(1, row5.ID);

							if (row5.Quantite_Commande == null) {
								pstmtInsert_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
							} else {
								pstmtInsert_tDBOutput_1.setInt(2, row5.Quantite_Commande);
							}

							if (row5.Cout_standard == null) {
								pstmtInsert_tDBOutput_1.setNull(3, java.sql.Types.FLOAT);
							} else {
								pstmtInsert_tDBOutput_1.setFloat(3, row5.Cout_standard);
							}

							if (row5.Nom_Client == null) {
								pstmtInsert_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(4, row5.Nom_Client);
							}

							if (row5.Ville == null) {
								pstmtInsert_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(5, row5.Ville);
							}

							if (row5.Nom_Produit == null) {
								pstmtInsert_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(6, row5.Nom_Produit);
							}

							if (row5.Couleur == null) {
								pstmtInsert_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(7, row5.Couleur);
							}

							if (row5.Prix == null) {
								pstmtInsert_tDBOutput_1.setNull(8, java.sql.Types.FLOAT);
							} else {
								pstmtInsert_tDBOutput_1.setFloat(8, row5.Prix);
							}

							if (row5.Modele == null) {
								pstmtInsert_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(9, row5.Modele);
							}

							if (row5.Sous_Categorie == null) {
								pstmtInsert_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(10, row5.Sous_Categorie);
							}

							if (row5.Categorie == null) {
								pstmtInsert_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(11, row5.Categorie);
							}

							if (row5.Type_Business == null) {
								pstmtInsert_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(12, row5.Type_Business);
							}

							if (row5.Type_Canal == null) {
								pstmtInsert_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(13, row5.Type_Canal);
							}

							if (row5.Region == null) {
								pstmtInsert_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(14, row5.Region);
							}

							if (row5.Pays == null) {
								pstmtInsert_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(15, row5.Pays);
							}

							if (row5.Continent == null) {
								pstmtInsert_tDBOutput_1.setNull(16, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(16, row5.Continent);
							}

							if (row5.Date != null) {
								pstmtInsert_tDBOutput_1.setTimestamp(17, new java.sql.Timestamp(row5.Date.getTime()));
							} else {
								pstmtInsert_tDBOutput_1.setNull(17, java.sql.Types.TIMESTAMP);
							}

							if (row5.Etat_Civil == null) {
								pstmtInsert_tDBOutput_1.setNull(18, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(18, row5.Etat_Civil);
							}

							if (row5.Genre == null) {
								pstmtInsert_tDBOutput_1.setNull(19, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(19, row5.Genre);
							}

							if (row5.Revenu_annuel == null) {
								pstmtInsert_tDBOutput_1.setNull(20, java.sql.Types.FLOAT);
							} else {
								pstmtInsert_tDBOutput_1.setFloat(20, row5.Revenu_annuel);
							}

							if (row5.Nbr_Enfants == null) {
								pstmtInsert_tDBOutput_1.setNull(21, java.sql.Types.INTEGER);
							} else {
								pstmtInsert_tDBOutput_1.setInt(21, row5.Nbr_Enfants);
							}

							if (row5.Poste == null) {
								pstmtInsert_tDBOutput_1.setNull(22, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(22, row5.Poste);
							}

							if (row5.Propeietaire_Maison == null) {
								pstmtInsert_tDBOutput_1.setNull(23, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(23, row5.Propeietaire_Maison);
							}

							if (row5.Nbr_Voitures == null) {
								pstmtInsert_tDBOutput_1.setNull(24, java.sql.Types.INTEGER);
							} else {
								pstmtInsert_tDBOutput_1.setInt(24, row5.Nbr_Voitures);
							}

							if (row5.Annee == null) {
								pstmtInsert_tDBOutput_1.setNull(25, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(25, row5.Annee);
							}

							if (row5.Mois == null) {
								pstmtInsert_tDBOutput_1.setNull(26, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(26, row5.Mois);
							}

							if (row5.Semaine == null) {
								pstmtInsert_tDBOutput_1.setNull(27, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(27, row5.Semaine);
							}

							if (row5.Saison == null) {
								pstmtInsert_tDBOutput_1.setNull(28, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(28, row5.Saison);
							}

							if (row5.Age == null) {
								pstmtInsert_tDBOutput_1.setNull(29, java.sql.Types.INTEGER);
							} else {
								pstmtInsert_tDBOutput_1.setInt(29, row5.Age);
							}

							if (row5.Tranche_d_age == null) {
								pstmtInsert_tDBOutput_1.setNull(30, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_1.setString(30, row5.Tranche_d_age);
							}

							try {

								int processedCount_tDBOutput_1 = pstmtInsert_tDBOutput_1.executeUpdate();
								insertedCount_tDBOutput_1 += processedCount_tDBOutput_1;
								rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
								nb_line_tDBOutput_1++;

							} catch (java.lang.Exception e) {
								globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

								whetherReject_tDBOutput_1 = true;
								nb_line_tDBOutput_1++;
								System.err.print(e.getMessage());
							}
						}
						commitCounter_tDBOutput_1++;
						if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {
							if (rowsToCommitCount_tDBOutput_1 != 0) {

							}
							conn_tDBOutput_1.commit();
							if (rowsToCommitCount_tDBOutput_1 != 0) {

								rowsToCommitCount_tDBOutput_1 = 0;
							}
							commitCounter_tDBOutput_1 = 0;
						}

						tos_count_tDBOutput_1++;

						/**
						 * [tDBOutput_1 main ] stop
						 */

						/**
						 * [tDBOutput_1 process_data_begin ] start
						 */

						currentComponent = "tDBOutput_1";

						/**
						 * [tDBOutput_1 process_data_begin ] stop
						 */

						/**
						 * [tDBOutput_1 process_data_end ] start
						 */

						currentComponent = "tDBOutput_1";

						/**
						 * [tDBOutput_1 process_data_end ] stop
						 */

						/**
						 * [tUnite_1 process_data_end ] start
						 */

						currentComponent = "tUnite_1";

						/**
						 * [tUnite_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 process_data_end ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 end ] start
						 */

						currentComponent = "tDBInput_2";

					}
				} finally {
					if (rs_tDBInput_2 != null) {
						rs_tDBInput_2.close();
					}
					if (stmt_tDBInput_2 != null) {
						stmt_tDBInput_2.close();
					}
					if (conn_tDBInput_2 != null && !conn_tDBInput_2.isClosed()) {

						conn_tDBInput_2.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}

				}

				globalMap.put("tDBInput_2_NB_LINE", nb_line_tDBInput_2);

				ok_Hash.put("tDBInput_2", true);
				end_Hash.put("tDBInput_2", System.currentTimeMillis());

				/**
				 * [tDBInput_2 end ] stop
				 */

				/**
				 * [tUnite_1 end ] start
				 */

				currentComponent = "tUnite_1";

				globalMap.put("tUnite_1_NB_LINE", nb_line_tUnite_1);
				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1", "row4", "row3", "row2");
				}

				ok_Hash.put("tUnite_1", true);
				end_Hash.put("tUnite_1", System.currentTimeMillis());

				/**
				 * [tUnite_1 end ] stop
				 */

				/**
				 * [tDBOutput_1 end ] start
				 */

				currentComponent = "tDBOutput_1";

				if (pstmtUpdate_tDBOutput_1 != null) {
					pstmtUpdate_tDBOutput_1.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_1");
				}
				if (pstmtInsert_tDBOutput_1 != null) {
					pstmtInsert_tDBOutput_1.close();
					resourceMap.remove("pstmtInsert_tDBOutput_1");
				}
				if (pstmt_tDBOutput_1 != null) {
					pstmt_tDBOutput_1.close();
					resourceMap.remove("pstmt_tDBOutput_1");
				}
				resourceMap.put("statementClosed_tDBOutput_1", true);
				if (rowsToCommitCount_tDBOutput_1 != 0) {

				}
				conn_tDBOutput_1.commit();
				if (rowsToCommitCount_tDBOutput_1 != 0) {

					rowsToCommitCount_tDBOutput_1 = 0;
				}
				commitCounter_tDBOutput_1 = 0;

				conn_tDBOutput_1.close();

				resourceMap.put("finish_tDBOutput_1", true);

				nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
				nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
				nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
				nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

				globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tDBInput_1 finally ] start
				 */

				currentComponent = "tDBInput_1";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tFileInputExcel_1 finally ] start
				 */

				currentComponent = "tFileInputExcel_1";

				/**
				 * [tFileInputExcel_1 finally ] stop
				 */

				/**
				 * [tDBInput_2 finally ] start
				 */

				currentComponent = "tDBInput_2";

				/**
				 * [tDBInput_2 finally ] stop
				 */

				/**
				 * [tUnite_1 finally ] start
				 */

				currentComponent = "tUnite_1";

				/**
				 * [tUnite_1 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
						java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_1 = null;
						if ((pstmtUpdateToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtUpdate_tDBOutput_1")) != null) {
							pstmtUpdateToClose_tDBOutput_1.close();
						}
						java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_1 = null;
						if ((pstmtInsertToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtInsert_tDBOutput_1")) != null) {
							pstmtInsertToClose_tDBOutput_1.close();
						}
						java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
						if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_1")) != null) {
							pstmtToClose_tDBOutput_1.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_1") == null) {
						java.sql.Connection ctn_tDBOutput_1 = null;
						if ((ctn_tDBOutput_1 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_1")) != null) {
							try {
								ctn_tDBOutput_1.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_1) {
								String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :"
										+ sqlEx_tDBOutput_1.getMessage();
								System.err.println(errorMessage_tDBOutput_1);
							}
						}
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final JOB_datawarehouse JOB_datawarehouseClass = new JOB_datawarehouse();

		int exitCode = JOB_datawarehouseClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = JOB_datawarehouse.class.getClassLoader()
					.getResourceAsStream("awc/job_datawarehouse_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = JOB_datawarehouse.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : JOB_datawarehouse");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 286813 characters generated by Talend Open Studio for Data Integration on the
 * 16 février 2023 à 15:52:00 WEST
 ************************************************************************************************/