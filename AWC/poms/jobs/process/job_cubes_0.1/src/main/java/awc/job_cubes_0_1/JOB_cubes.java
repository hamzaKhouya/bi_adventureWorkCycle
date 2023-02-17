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

package awc.job_cubes_0_1;

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
 * Job: JOB_cubes Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class JOB_cubes implements TalendJob {

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
	private final String jobName = "JOB_cubes";
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
					JOB_cubes.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(JOB_cubes.this, new Object[] { e, currentComponent, globalMap });
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

	public void tDBInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class cube_commandeStruct implements routines.system.IPersistableRow<cube_commandeStruct> {
		final static byte[] commonByteArrayLock_AWC_JOB_cubes = new byte[0];
		static byte[] commonByteArray_AWC_JOB_cubes = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID_commande;

		public int getID_commande() {
			return this.ID_commande;
		}

		public Integer Quantite_Commande;

		public Integer getQuantite_Commande() {
			return this.Quantite_Commande;
		}

		public String Type_Business;

		public String getType_Business() {
			return this.Type_Business;
		}

		public String Type_Canal;

		public String getType_Canal() {
			return this.Type_Canal;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID_commande;

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
			final cube_commandeStruct other = (cube_commandeStruct) obj;

			if (this.ID_commande != other.ID_commande)
				return false;

			return true;
		}

		public void copyDataTo(cube_commandeStruct other) {

			other.ID_commande = this.ID_commande;
			other.Quantite_Commande = this.Quantite_Commande;
			other.Type_Business = this.Type_Business;
			other.Type_Canal = this.Type_Canal;

		}

		public void copyKeysDataTo(cube_commandeStruct other) {

			other.ID_commande = this.ID_commande;

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
				if (length > commonByteArray_AWC_JOB_cubes.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_cubes.length == 0) {
						commonByteArray_AWC_JOB_cubes = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_cubes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AWC_JOB_cubes, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_cubes, 0, length, utf8Charset);
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
				if (length > commonByteArray_AWC_JOB_cubes.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_cubes.length == 0) {
						commonByteArray_AWC_JOB_cubes = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_cubes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AWC_JOB_cubes, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_cubes, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_AWC_JOB_cubes) {

				try {

					int length = 0;

					this.ID_commande = dis.readInt();

					this.Quantite_Commande = readInteger(dis);

					this.Type_Business = readString(dis);

					this.Type_Canal = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_AWC_JOB_cubes) {

				try {

					int length = 0;

					this.ID_commande = dis.readInt();

					this.Quantite_Commande = readInteger(dis);

					this.Type_Business = readString(dis);

					this.Type_Canal = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_commande);

				// Integer

				writeInteger(this.Quantite_Commande, dos);

				// String

				writeString(this.Type_Business, dos);

				// String

				writeString(this.Type_Canal, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_commande);

				// Integer

				writeInteger(this.Quantite_Commande, dos);

				// String

				writeString(this.Type_Business, dos);

				// String

				writeString(this.Type_Canal, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_commande=" + String.valueOf(ID_commande));
			sb.append(",Quantite_Commande=" + String.valueOf(Quantite_Commande));
			sb.append(",Type_Business=" + Type_Business);
			sb.append(",Type_Canal=" + Type_Canal);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(cube_commandeStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_commande, other.ID_commande);
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

	public static class cube_clientStruct implements routines.system.IPersistableRow<cube_clientStruct> {
		final static byte[] commonByteArrayLock_AWC_JOB_cubes = new byte[0];
		static byte[] commonByteArray_AWC_JOB_cubes = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID_client;

		public int getID_client() {
			return this.ID_client;
		}

		public String Nom_Client;

		public String getNom_Client() {
			return this.Nom_Client;
		}

		public String Genre;

		public String getGenre() {
			return this.Genre;
		}

		public String Etat_Civil;

		public String getEtat_Civil() {
			return this.Etat_Civil;
		}

		public Float Revenu_annuel;

		public Float getRevenu_annuel() {
			return this.Revenu_annuel;
		}

		public String Poste;

		public String getPoste() {
			return this.Poste;
		}

		public Integer Nbr_Enfants;

		public Integer getNbr_Enfants() {
			return this.Nbr_Enfants;
		}

		public String Propeietaire_Maison;

		public String getPropeietaire_Maison() {
			return this.Propeietaire_Maison;
		}

		public Integer Nbr_Voitures;

		public Integer getNbr_Voitures() {
			return this.Nbr_Voitures;
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

				result = prime * result + (int) this.ID_client;

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
			final cube_clientStruct other = (cube_clientStruct) obj;

			if (this.ID_client != other.ID_client)
				return false;

			return true;
		}

		public void copyDataTo(cube_clientStruct other) {

			other.ID_client = this.ID_client;
			other.Nom_Client = this.Nom_Client;
			other.Genre = this.Genre;
			other.Etat_Civil = this.Etat_Civil;
			other.Revenu_annuel = this.Revenu_annuel;
			other.Poste = this.Poste;
			other.Nbr_Enfants = this.Nbr_Enfants;
			other.Propeietaire_Maison = this.Propeietaire_Maison;
			other.Nbr_Voitures = this.Nbr_Voitures;
			other.Age = this.Age;
			other.Tranche_d_age = this.Tranche_d_age;

		}

		public void copyKeysDataTo(cube_clientStruct other) {

			other.ID_client = this.ID_client;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_cubes.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_cubes.length == 0) {
						commonByteArray_AWC_JOB_cubes = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_cubes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AWC_JOB_cubes, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_cubes, 0, length, utf8Charset);
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
				if (length > commonByteArray_AWC_JOB_cubes.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_cubes.length == 0) {
						commonByteArray_AWC_JOB_cubes = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_cubes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AWC_JOB_cubes, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_cubes, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_AWC_JOB_cubes) {

				try {

					int length = 0;

					this.ID_client = dis.readInt();

					this.Nom_Client = readString(dis);

					this.Genre = readString(dis);

					this.Etat_Civil = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Revenu_annuel = null;
					} else {
						this.Revenu_annuel = dis.readFloat();
					}

					this.Poste = readString(dis);

					this.Nbr_Enfants = readInteger(dis);

					this.Propeietaire_Maison = readString(dis);

					this.Nbr_Voitures = readInteger(dis);

					this.Age = readInteger(dis);

					this.Tranche_d_age = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_AWC_JOB_cubes) {

				try {

					int length = 0;

					this.ID_client = dis.readInt();

					this.Nom_Client = readString(dis);

					this.Genre = readString(dis);

					this.Etat_Civil = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Revenu_annuel = null;
					} else {
						this.Revenu_annuel = dis.readFloat();
					}

					this.Poste = readString(dis);

					this.Nbr_Enfants = readInteger(dis);

					this.Propeietaire_Maison = readString(dis);

					this.Nbr_Voitures = readInteger(dis);

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

				dos.writeInt(this.ID_client);

				// String

				writeString(this.Nom_Client, dos);

				// String

				writeString(this.Genre, dos);

				// String

				writeString(this.Etat_Civil, dos);

				// Float

				if (this.Revenu_annuel == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Revenu_annuel);
				}

				// String

				writeString(this.Poste, dos);

				// Integer

				writeInteger(this.Nbr_Enfants, dos);

				// String

				writeString(this.Propeietaire_Maison, dos);

				// Integer

				writeInteger(this.Nbr_Voitures, dos);

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

				dos.writeInt(this.ID_client);

				// String

				writeString(this.Nom_Client, dos);

				// String

				writeString(this.Genre, dos);

				// String

				writeString(this.Etat_Civil, dos);

				// Float

				if (this.Revenu_annuel == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Revenu_annuel);
				}

				// String

				writeString(this.Poste, dos);

				// Integer

				writeInteger(this.Nbr_Enfants, dos);

				// String

				writeString(this.Propeietaire_Maison, dos);

				// Integer

				writeInteger(this.Nbr_Voitures, dos);

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
			sb.append("ID_client=" + String.valueOf(ID_client));
			sb.append(",Nom_Client=" + Nom_Client);
			sb.append(",Genre=" + Genre);
			sb.append(",Etat_Civil=" + Etat_Civil);
			sb.append(",Revenu_annuel=" + String.valueOf(Revenu_annuel));
			sb.append(",Poste=" + Poste);
			sb.append(",Nbr_Enfants=" + String.valueOf(Nbr_Enfants));
			sb.append(",Propeietaire_Maison=" + Propeietaire_Maison);
			sb.append(",Nbr_Voitures=" + String.valueOf(Nbr_Voitures));
			sb.append(",Age=" + String.valueOf(Age));
			sb.append(",Tranche_d_age=" + Tranche_d_age);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(cube_clientStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_client, other.ID_client);
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

	public static class cube_produitStruct implements routines.system.IPersistableRow<cube_produitStruct> {
		final static byte[] commonByteArrayLock_AWC_JOB_cubes = new byte[0];
		static byte[] commonByteArray_AWC_JOB_cubes = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID_produit;

		public int getID_produit() {
			return this.ID_produit;
		}

		public String Nom_Produit;

		public String getNom_Produit() {
			return this.Nom_Produit;
		}

		public Float Cout_standard;

		public Float getCout_standard() {
			return this.Cout_standard;
		}

		public Float Prix;

		public Float getPrix() {
			return this.Prix;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		public String Modele;

		public String getModele() {
			return this.Modele;
		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		public String Sous_Categorie;

		public String getSous_Categorie() {
			return this.Sous_Categorie;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID_produit;

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
			final cube_produitStruct other = (cube_produitStruct) obj;

			if (this.ID_produit != other.ID_produit)
				return false;

			return true;
		}

		public void copyDataTo(cube_produitStruct other) {

			other.ID_produit = this.ID_produit;
			other.Nom_Produit = this.Nom_Produit;
			other.Cout_standard = this.Cout_standard;
			other.Prix = this.Prix;
			other.Couleur = this.Couleur;
			other.Modele = this.Modele;
			other.Categorie = this.Categorie;
			other.Sous_Categorie = this.Sous_Categorie;

		}

		public void copyKeysDataTo(cube_produitStruct other) {

			other.ID_produit = this.ID_produit;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_cubes.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_cubes.length == 0) {
						commonByteArray_AWC_JOB_cubes = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_cubes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AWC_JOB_cubes, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_cubes, 0, length, utf8Charset);
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
				if (length > commonByteArray_AWC_JOB_cubes.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_cubes.length == 0) {
						commonByteArray_AWC_JOB_cubes = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_cubes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AWC_JOB_cubes, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_cubes, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_AWC_JOB_cubes) {

				try {

					int length = 0;

					this.ID_produit = dis.readInt();

					this.Nom_Produit = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Cout_standard = null;
					} else {
						this.Cout_standard = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Prix = null;
					} else {
						this.Prix = dis.readFloat();
					}

					this.Couleur = readString(dis);

					this.Modele = readString(dis);

					this.Categorie = readString(dis);

					this.Sous_Categorie = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_AWC_JOB_cubes) {

				try {

					int length = 0;

					this.ID_produit = dis.readInt();

					this.Nom_Produit = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Cout_standard = null;
					} else {
						this.Cout_standard = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Prix = null;
					} else {
						this.Prix = dis.readFloat();
					}

					this.Couleur = readString(dis);

					this.Modele = readString(dis);

					this.Categorie = readString(dis);

					this.Sous_Categorie = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_produit);

				// String

				writeString(this.Nom_Produit, dos);

				// Float

				if (this.Cout_standard == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Cout_standard);
				}

				// Float

				if (this.Prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix);
				}

				// String

				writeString(this.Couleur, dos);

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Sous_Categorie, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_produit);

				// String

				writeString(this.Nom_Produit, dos);

				// Float

				if (this.Cout_standard == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Cout_standard);
				}

				// Float

				if (this.Prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix);
				}

				// String

				writeString(this.Couleur, dos);

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Sous_Categorie, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_produit=" + String.valueOf(ID_produit));
			sb.append(",Nom_Produit=" + Nom_Produit);
			sb.append(",Cout_standard=" + String.valueOf(Cout_standard));
			sb.append(",Prix=" + String.valueOf(Prix));
			sb.append(",Couleur=" + Couleur);
			sb.append(",Modele=" + Modele);
			sb.append(",Categorie=" + Categorie);
			sb.append(",Sous_Categorie=" + Sous_Categorie);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(cube_produitStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_produit, other.ID_produit);
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

	public static class cube_dateStruct implements routines.system.IPersistableRow<cube_dateStruct> {
		final static byte[] commonByteArrayLock_AWC_JOB_cubes = new byte[0];
		static byte[] commonByteArray_AWC_JOB_cubes = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID_date;

		public int getID_date() {
			return this.ID_date;
		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
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

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID_date;

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
			final cube_dateStruct other = (cube_dateStruct) obj;

			if (this.ID_date != other.ID_date)
				return false;

			return true;
		}

		public void copyDataTo(cube_dateStruct other) {

			other.ID_date = this.ID_date;
			other.Date = this.Date;
			other.Annee = this.Annee;
			other.Mois = this.Mois;
			other.Semaine = this.Semaine;
			other.Saison = this.Saison;

		}

		public void copyKeysDataTo(cube_dateStruct other) {

			other.ID_date = this.ID_date;

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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_cubes.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_cubes.length == 0) {
						commonByteArray_AWC_JOB_cubes = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_cubes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AWC_JOB_cubes, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_cubes, 0, length, utf8Charset);
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
				if (length > commonByteArray_AWC_JOB_cubes.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_cubes.length == 0) {
						commonByteArray_AWC_JOB_cubes = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_cubes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AWC_JOB_cubes, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_cubes, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_AWC_JOB_cubes) {

				try {

					int length = 0;

					this.ID_date = dis.readInt();

					this.Date = readDate(dis);

					this.Annee = readString(dis);

					this.Mois = readString(dis);

					this.Semaine = readString(dis);

					this.Saison = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_AWC_JOB_cubes) {

				try {

					int length = 0;

					this.ID_date = dis.readInt();

					this.Date = readDate(dis);

					this.Annee = readString(dis);

					this.Mois = readString(dis);

					this.Semaine = readString(dis);

					this.Saison = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_date);

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.Annee, dos);

				// String

				writeString(this.Mois, dos);

				// String

				writeString(this.Semaine, dos);

				// String

				writeString(this.Saison, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_date);

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.Annee, dos);

				// String

				writeString(this.Mois, dos);

				// String

				writeString(this.Semaine, dos);

				// String

				writeString(this.Saison, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_date=" + String.valueOf(ID_date));
			sb.append(",Date=" + String.valueOf(Date));
			sb.append(",Annee=" + Annee);
			sb.append(",Mois=" + Mois);
			sb.append(",Semaine=" + Semaine);
			sb.append(",Saison=" + Saison);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(cube_dateStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_date, other.ID_date);
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

	public static class cube_lieuStruct implements routines.system.IPersistableRow<cube_lieuStruct> {
		final static byte[] commonByteArrayLock_AWC_JOB_cubes = new byte[0];
		static byte[] commonByteArray_AWC_JOB_cubes = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int ID_lieu;

		public int getID_lieu() {
			return this.ID_lieu;
		}

		public String Ville;

		public String getVille() {
			return this.Ville;
		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public String Continent;

		public String getContinent() {
			return this.Continent;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.ID_lieu;

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
			final cube_lieuStruct other = (cube_lieuStruct) obj;

			if (this.ID_lieu != other.ID_lieu)
				return false;

			return true;
		}

		public void copyDataTo(cube_lieuStruct other) {

			other.ID_lieu = this.ID_lieu;
			other.Ville = this.Ville;
			other.Pays = this.Pays;
			other.Continent = this.Continent;
			other.Region = this.Region;

		}

		public void copyKeysDataTo(cube_lieuStruct other) {

			other.ID_lieu = this.ID_lieu;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AWC_JOB_cubes.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_cubes.length == 0) {
						commonByteArray_AWC_JOB_cubes = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_cubes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AWC_JOB_cubes, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_cubes, 0, length, utf8Charset);
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
				if (length > commonByteArray_AWC_JOB_cubes.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_cubes.length == 0) {
						commonByteArray_AWC_JOB_cubes = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_cubes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AWC_JOB_cubes, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_cubes, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_AWC_JOB_cubes) {

				try {

					int length = 0;

					this.ID_lieu = dis.readInt();

					this.Ville = readString(dis);

					this.Pays = readString(dis);

					this.Continent = readString(dis);

					this.Region = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_AWC_JOB_cubes) {

				try {

					int length = 0;

					this.ID_lieu = dis.readInt();

					this.Ville = readString(dis);

					this.Pays = readString(dis);

					this.Continent = readString(dis);

					this.Region = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.ID_lieu);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Continent, dos);

				// String

				writeString(this.Region, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.ID_lieu);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Continent, dos);

				// String

				writeString(this.Region, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_lieu=" + String.valueOf(ID_lieu));
			sb.append(",Ville=" + Ville);
			sb.append(",Pays=" + Pays);
			sb.append(",Continent=" + Continent);
			sb.append(",Region=" + Region);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(cube_lieuStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_lieu, other.ID_lieu);
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
		final static byte[] commonByteArrayLock_AWC_JOB_cubes = new byte[0];
		static byte[] commonByteArray_AWC_JOB_cubes = new byte[0];

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
				if (length > commonByteArray_AWC_JOB_cubes.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_cubes.length == 0) {
						commonByteArray_AWC_JOB_cubes = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_cubes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AWC_JOB_cubes, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_cubes, 0, length, utf8Charset);
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
				if (length > commonByteArray_AWC_JOB_cubes.length) {
					if (length < 1024 && commonByteArray_AWC_JOB_cubes.length == 0) {
						commonByteArray_AWC_JOB_cubes = new byte[1024];
					} else {
						commonByteArray_AWC_JOB_cubes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AWC_JOB_cubes, 0, length);
				strReturn = new String(commonByteArray_AWC_JOB_cubes, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_AWC_JOB_cubes) {

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

			synchronized (commonByteArrayLock_AWC_JOB_cubes) {

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

	public void tDBInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 0);

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
				cube_commandeStruct cube_commande = new cube_commandeStruct();
				cube_clientStruct cube_client = new cube_clientStruct();
				cube_produitStruct cube_produit = new cube_produitStruct();
				cube_dateStruct cube_date = new cube_dateStruct();
				cube_lieuStruct cube_lieu = new cube_lieuStruct();

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "cube_commande");
				}

				int tos_count_tDBOutput_1 = 0;

				String dbschema_tDBOutput_1 = null;
				dbschema_tDBOutput_1 = "public";

				String tableName_tDBOutput_1 = null;
				if (dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
					tableName_tDBOutput_1 = ("commandes");
				} else {
					tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "\".\"" + ("commandes");
				}

				int updateKeyCount_tDBOutput_1 = 1;
				if (updateKeyCount_tDBOutput_1 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_1 == 4 && true) {
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

				String url_tDBOutput_1 = "jdbc:postgresql://" + "localhost" + ":" + "5432" + "/" + "dimension_db";
				dbUser_tDBOutput_1 = "postgres";

				final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:rfB4zuc0OJc+gvOjDMt3kyKELEOKWQ/Msz7ZIVd8vPkys4qO/w==");

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
						if (table_tDBOutput_1.equals(("commandes")) && (schema_tDBOutput_1.equals(dbschema_tDBOutput_1)
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
							+ "\"(\"ID_commande\" INT4  not null ,\"Quantite_Commande\" INT4 ,\"Type_Business\" VARCHAR(50)  ,\"Type_Canal\" VARCHAR(50)  ,primary key(\"ID_commande\"))");
				}
				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(
						"SELECT COUNT(1) FROM \"" + tableName_tDBOutput_1 + "\" WHERE \"ID_commande\" = ?");
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);
				String insert_tDBOutput_1 = "INSERT INTO \"" + tableName_tDBOutput_1
						+ "\" (\"ID_commande\",\"Quantite_Commande\",\"Type_Business\",\"Type_Canal\") VALUES (?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmtInsert_tDBOutput_1", pstmtInsert_tDBOutput_1);
				String update_tDBOutput_1 = "UPDATE \"" + tableName_tDBOutput_1
						+ "\" SET \"Quantite_Commande\" = ?,\"Type_Business\" = ?,\"Type_Canal\" = ? WHERE \"ID_commande\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement(update_tDBOutput_1);
				resourceMap.put("pstmtUpdate_tDBOutput_1", pstmtUpdate_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tDBOutput_2 begin ] start
				 */

				ok_Hash.put("tDBOutput_2", false);
				start_Hash.put("tDBOutput_2", System.currentTimeMillis());

				currentComponent = "tDBOutput_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "cube_client");
				}

				int tos_count_tDBOutput_2 = 0;

				String dbschema_tDBOutput_2 = null;
				dbschema_tDBOutput_2 = "public";

				String tableName_tDBOutput_2 = null;
				if (dbschema_tDBOutput_2 == null || dbschema_tDBOutput_2.trim().length() == 0) {
					tableName_tDBOutput_2 = ("client");
				} else {
					tableName_tDBOutput_2 = dbschema_tDBOutput_2 + "\".\"" + ("client");
				}

				int updateKeyCount_tDBOutput_2 = 1;
				if (updateKeyCount_tDBOutput_2 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_2 == 11 && true) {
					System.err.println("For update, every Schema column can not be a key");
				}

				int nb_line_tDBOutput_2 = 0;
				int nb_line_update_tDBOutput_2 = 0;
				int nb_line_inserted_tDBOutput_2 = 0;
				int nb_line_deleted_tDBOutput_2 = 0;
				int nb_line_rejected_tDBOutput_2 = 0;

				int deletedCount_tDBOutput_2 = 0;
				int updatedCount_tDBOutput_2 = 0;
				int insertedCount_tDBOutput_2 = 0;
				int rowsToCommitCount_tDBOutput_2 = 0;
				int rejectedCount_tDBOutput_2 = 0;

				boolean whetherReject_tDBOutput_2 = false;

				java.sql.Connection conn_tDBOutput_2 = null;
				String dbUser_tDBOutput_2 = null;

				java.lang.Class.forName("org.postgresql.Driver");

				String url_tDBOutput_2 = "jdbc:postgresql://" + "localhost" + ":" + "5432" + "/" + "dimension_db";
				dbUser_tDBOutput_2 = "postgres";

				final String decryptedPassword_tDBOutput_2 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:KppPA5M2/baxuSCgyrjvI8zL0/vkDwFmIfXDHeBFrGaI8z8jIQ==");

				String dbPwd_tDBOutput_2 = decryptedPassword_tDBOutput_2;

				conn_tDBOutput_2 = java.sql.DriverManager.getConnection(url_tDBOutput_2, dbUser_tDBOutput_2,
						dbPwd_tDBOutput_2);

				resourceMap.put("conn_tDBOutput_2", conn_tDBOutput_2);
				conn_tDBOutput_2.setAutoCommit(false);
				int commitEvery_tDBOutput_2 = 10000;
				int commitCounter_tDBOutput_2 = 0;

				int count_tDBOutput_2 = 0;
				java.sql.DatabaseMetaData dbMetaData_tDBOutput_2 = conn_tDBOutput_2.getMetaData();
				boolean whetherExist_tDBOutput_2 = false;
				try (java.sql.ResultSet rsTable_tDBOutput_2 = dbMetaData_tDBOutput_2.getTables(null, null, null,
						new String[] { "TABLE" })) {
					String defaultSchema_tDBOutput_2 = "public";
					if (dbschema_tDBOutput_2 == null || dbschema_tDBOutput_2.trim().length() == 0) {
						try (java.sql.Statement stmtSchema_tDBOutput_2 = conn_tDBOutput_2.createStatement();
								java.sql.ResultSet rsSchema_tDBOutput_2 = stmtSchema_tDBOutput_2
										.executeQuery("select current_schema() ")) {
							while (rsSchema_tDBOutput_2.next()) {
								defaultSchema_tDBOutput_2 = rsSchema_tDBOutput_2.getString("current_schema");
							}
						}
					}
					while (rsTable_tDBOutput_2.next()) {
						String table_tDBOutput_2 = rsTable_tDBOutput_2.getString("TABLE_NAME");
						String schema_tDBOutput_2 = rsTable_tDBOutput_2.getString("TABLE_SCHEM");
						if (table_tDBOutput_2.equals(("client")) && (schema_tDBOutput_2.equals(dbschema_tDBOutput_2)
								|| ((dbschema_tDBOutput_2 == null || dbschema_tDBOutput_2.trim().length() == 0)
										&& defaultSchema_tDBOutput_2.equals(schema_tDBOutput_2)))) {
							whetherExist_tDBOutput_2 = true;
							break;
						}
					}
				}
				if (whetherExist_tDBOutput_2) {
					try (java.sql.Statement stmtDrop_tDBOutput_2 = conn_tDBOutput_2.createStatement()) {
						stmtDrop_tDBOutput_2.execute("DROP TABLE \"" + tableName_tDBOutput_2 + "\"");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_2 = conn_tDBOutput_2.createStatement()) {
					stmtCreate_tDBOutput_2.execute("CREATE TABLE \"" + tableName_tDBOutput_2
							+ "\"(\"ID_client\" INT4  not null ,\"Nom_Client\" VARCHAR(50)  ,\"Genre\" VARCHAR(50)  ,\"Etat_Civil\" VARCHAR(50)  ,\"Revenu_annuel\" FLOAT4 ,\"Poste\" VARCHAR(50)  ,\"Nbr_Enfants\" INT4 ,\"Propeietaire_Maison\" VARCHAR(3)  ,\"Nbr_Voitures\" INT4 ,\"Age\" INT4 ,\"Tranche_d_age\" VARCHAR(5)  ,primary key(\"ID_client\"))");
				}
				java.sql.PreparedStatement pstmt_tDBOutput_2 = conn_tDBOutput_2.prepareStatement(
						"SELECT COUNT(1) FROM \"" + tableName_tDBOutput_2 + "\" WHERE \"ID_client\" = ?");
				resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);
				String insert_tDBOutput_2 = "INSERT INTO \"" + tableName_tDBOutput_2
						+ "\" (\"ID_client\",\"Nom_Client\",\"Genre\",\"Etat_Civil\",\"Revenu_annuel\",\"Poste\",\"Nbr_Enfants\",\"Propeietaire_Maison\",\"Nbr_Voitures\",\"Age\",\"Tranche_d_age\") VALUES (?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_2 = conn_tDBOutput_2
						.prepareStatement(insert_tDBOutput_2);
				resourceMap.put("pstmtInsert_tDBOutput_2", pstmtInsert_tDBOutput_2);
				String update_tDBOutput_2 = "UPDATE \"" + tableName_tDBOutput_2
						+ "\" SET \"Nom_Client\" = ?,\"Genre\" = ?,\"Etat_Civil\" = ?,\"Revenu_annuel\" = ?,\"Poste\" = ?,\"Nbr_Enfants\" = ?,\"Propeietaire_Maison\" = ?,\"Nbr_Voitures\" = ?,\"Age\" = ?,\"Tranche_d_age\" = ? WHERE \"ID_client\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_2 = conn_tDBOutput_2
						.prepareStatement(update_tDBOutput_2);
				resourceMap.put("pstmtUpdate_tDBOutput_2", pstmtUpdate_tDBOutput_2);

				/**
				 * [tDBOutput_2 begin ] stop
				 */

				/**
				 * [tDBOutput_3 begin ] start
				 */

				ok_Hash.put("tDBOutput_3", false);
				start_Hash.put("tDBOutput_3", System.currentTimeMillis());

				currentComponent = "tDBOutput_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "cube_produit");
				}

				int tos_count_tDBOutput_3 = 0;

				String dbschema_tDBOutput_3 = null;
				dbschema_tDBOutput_3 = "public";

				String tableName_tDBOutput_3 = null;
				if (dbschema_tDBOutput_3 == null || dbschema_tDBOutput_3.trim().length() == 0) {
					tableName_tDBOutput_3 = ("produit");
				} else {
					tableName_tDBOutput_3 = dbschema_tDBOutput_3 + "\".\"" + ("produit");
				}

				int updateKeyCount_tDBOutput_3 = 1;
				if (updateKeyCount_tDBOutput_3 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_3 == 8 && true) {
					System.err.println("For update, every Schema column can not be a key");
				}

				int nb_line_tDBOutput_3 = 0;
				int nb_line_update_tDBOutput_3 = 0;
				int nb_line_inserted_tDBOutput_3 = 0;
				int nb_line_deleted_tDBOutput_3 = 0;
				int nb_line_rejected_tDBOutput_3 = 0;

				int deletedCount_tDBOutput_3 = 0;
				int updatedCount_tDBOutput_3 = 0;
				int insertedCount_tDBOutput_3 = 0;
				int rowsToCommitCount_tDBOutput_3 = 0;
				int rejectedCount_tDBOutput_3 = 0;

				boolean whetherReject_tDBOutput_3 = false;

				java.sql.Connection conn_tDBOutput_3 = null;
				String dbUser_tDBOutput_3 = null;

				java.lang.Class.forName("org.postgresql.Driver");

				String url_tDBOutput_3 = "jdbc:postgresql://" + "localhost" + ":" + "5432" + "/" + "dimension_db";
				dbUser_tDBOutput_3 = "postgres";

				final String decryptedPassword_tDBOutput_3 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:qWj4l4fYK5f1vM3iIFbjgOQBeb6af5R+Gr2u1cE+7qjBbaoA9A==");

				String dbPwd_tDBOutput_3 = decryptedPassword_tDBOutput_3;

				conn_tDBOutput_3 = java.sql.DriverManager.getConnection(url_tDBOutput_3, dbUser_tDBOutput_3,
						dbPwd_tDBOutput_3);

				resourceMap.put("conn_tDBOutput_3", conn_tDBOutput_3);
				conn_tDBOutput_3.setAutoCommit(false);
				int commitEvery_tDBOutput_3 = 10000;
				int commitCounter_tDBOutput_3 = 0;

				int count_tDBOutput_3 = 0;
				java.sql.DatabaseMetaData dbMetaData_tDBOutput_3 = conn_tDBOutput_3.getMetaData();
				boolean whetherExist_tDBOutput_3 = false;
				try (java.sql.ResultSet rsTable_tDBOutput_3 = dbMetaData_tDBOutput_3.getTables(null, null, null,
						new String[] { "TABLE" })) {
					String defaultSchema_tDBOutput_3 = "public";
					if (dbschema_tDBOutput_3 == null || dbschema_tDBOutput_3.trim().length() == 0) {
						try (java.sql.Statement stmtSchema_tDBOutput_3 = conn_tDBOutput_3.createStatement();
								java.sql.ResultSet rsSchema_tDBOutput_3 = stmtSchema_tDBOutput_3
										.executeQuery("select current_schema() ")) {
							while (rsSchema_tDBOutput_3.next()) {
								defaultSchema_tDBOutput_3 = rsSchema_tDBOutput_3.getString("current_schema");
							}
						}
					}
					while (rsTable_tDBOutput_3.next()) {
						String table_tDBOutput_3 = rsTable_tDBOutput_3.getString("TABLE_NAME");
						String schema_tDBOutput_3 = rsTable_tDBOutput_3.getString("TABLE_SCHEM");
						if (table_tDBOutput_3.equals(("produit")) && (schema_tDBOutput_3.equals(dbschema_tDBOutput_3)
								|| ((dbschema_tDBOutput_3 == null || dbschema_tDBOutput_3.trim().length() == 0)
										&& defaultSchema_tDBOutput_3.equals(schema_tDBOutput_3)))) {
							whetherExist_tDBOutput_3 = true;
							break;
						}
					}
				}
				if (whetherExist_tDBOutput_3) {
					try (java.sql.Statement stmtDrop_tDBOutput_3 = conn_tDBOutput_3.createStatement()) {
						stmtDrop_tDBOutput_3.execute("DROP TABLE \"" + tableName_tDBOutput_3 + "\"");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_3 = conn_tDBOutput_3.createStatement()) {
					stmtCreate_tDBOutput_3.execute("CREATE TABLE \"" + tableName_tDBOutput_3
							+ "\"(\"ID_produit\" INT4  not null ,\"Nom_Produit\" VARCHAR(50)  ,\"Cout_standard\" FLOAT4 ,\"Prix\" FLOAT4 ,\"Couleur\" VARCHAR(50)  ,\"Modele\" VARCHAR(50)  ,\"Categorie\" VARCHAR(50)  ,\"Sous_Categorie\" VARCHAR(50)  ,primary key(\"ID_produit\"))");
				}
				java.sql.PreparedStatement pstmt_tDBOutput_3 = conn_tDBOutput_3.prepareStatement(
						"SELECT COUNT(1) FROM \"" + tableName_tDBOutput_3 + "\" WHERE \"ID_produit\" = ?");
				resourceMap.put("pstmt_tDBOutput_3", pstmt_tDBOutput_3);
				String insert_tDBOutput_3 = "INSERT INTO \"" + tableName_tDBOutput_3
						+ "\" (\"ID_produit\",\"Nom_Produit\",\"Cout_standard\",\"Prix\",\"Couleur\",\"Modele\",\"Categorie\",\"Sous_Categorie\") VALUES (?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_3 = conn_tDBOutput_3
						.prepareStatement(insert_tDBOutput_3);
				resourceMap.put("pstmtInsert_tDBOutput_3", pstmtInsert_tDBOutput_3);
				String update_tDBOutput_3 = "UPDATE \"" + tableName_tDBOutput_3
						+ "\" SET \"Nom_Produit\" = ?,\"Cout_standard\" = ?,\"Prix\" = ?,\"Couleur\" = ?,\"Modele\" = ?,\"Categorie\" = ?,\"Sous_Categorie\" = ? WHERE \"ID_produit\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_3 = conn_tDBOutput_3
						.prepareStatement(update_tDBOutput_3);
				resourceMap.put("pstmtUpdate_tDBOutput_3", pstmtUpdate_tDBOutput_3);

				/**
				 * [tDBOutput_3 begin ] stop
				 */

				/**
				 * [tDBOutput_4 begin ] start
				 */

				ok_Hash.put("tDBOutput_4", false);
				start_Hash.put("tDBOutput_4", System.currentTimeMillis());

				currentComponent = "tDBOutput_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "cube_date");
				}

				int tos_count_tDBOutput_4 = 0;

				String dbschema_tDBOutput_4 = null;
				dbschema_tDBOutput_4 = "public";

				String tableName_tDBOutput_4 = null;
				if (dbschema_tDBOutput_4 == null || dbschema_tDBOutput_4.trim().length() == 0) {
					tableName_tDBOutput_4 = ("date");
				} else {
					tableName_tDBOutput_4 = dbschema_tDBOutput_4 + "\".\"" + ("date");
				}

				int updateKeyCount_tDBOutput_4 = 1;
				if (updateKeyCount_tDBOutput_4 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_4 == 6 && true) {
					System.err.println("For update, every Schema column can not be a key");
				}

				int nb_line_tDBOutput_4 = 0;
				int nb_line_update_tDBOutput_4 = 0;
				int nb_line_inserted_tDBOutput_4 = 0;
				int nb_line_deleted_tDBOutput_4 = 0;
				int nb_line_rejected_tDBOutput_4 = 0;

				int deletedCount_tDBOutput_4 = 0;
				int updatedCount_tDBOutput_4 = 0;
				int insertedCount_tDBOutput_4 = 0;
				int rowsToCommitCount_tDBOutput_4 = 0;
				int rejectedCount_tDBOutput_4 = 0;

				boolean whetherReject_tDBOutput_4 = false;

				java.sql.Connection conn_tDBOutput_4 = null;
				String dbUser_tDBOutput_4 = null;

				java.lang.Class.forName("org.postgresql.Driver");

				String url_tDBOutput_4 = "jdbc:postgresql://" + "localhost" + ":" + "5432" + "/" + "dimension_db";
				dbUser_tDBOutput_4 = "postgres";

				final String decryptedPassword_tDBOutput_4 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:dDRM3taCNc1EekAZY0ZGuTizz4ecRErzpE/eu7e4oxCOSRPkeQ==");

				String dbPwd_tDBOutput_4 = decryptedPassword_tDBOutput_4;

				conn_tDBOutput_4 = java.sql.DriverManager.getConnection(url_tDBOutput_4, dbUser_tDBOutput_4,
						dbPwd_tDBOutput_4);

				resourceMap.put("conn_tDBOutput_4", conn_tDBOutput_4);
				conn_tDBOutput_4.setAutoCommit(false);
				int commitEvery_tDBOutput_4 = 10000;
				int commitCounter_tDBOutput_4 = 0;

				int count_tDBOutput_4 = 0;
				java.sql.DatabaseMetaData dbMetaData_tDBOutput_4 = conn_tDBOutput_4.getMetaData();
				boolean whetherExist_tDBOutput_4 = false;
				try (java.sql.ResultSet rsTable_tDBOutput_4 = dbMetaData_tDBOutput_4.getTables(null, null, null,
						new String[] { "TABLE" })) {
					String defaultSchema_tDBOutput_4 = "public";
					if (dbschema_tDBOutput_4 == null || dbschema_tDBOutput_4.trim().length() == 0) {
						try (java.sql.Statement stmtSchema_tDBOutput_4 = conn_tDBOutput_4.createStatement();
								java.sql.ResultSet rsSchema_tDBOutput_4 = stmtSchema_tDBOutput_4
										.executeQuery("select current_schema() ")) {
							while (rsSchema_tDBOutput_4.next()) {
								defaultSchema_tDBOutput_4 = rsSchema_tDBOutput_4.getString("current_schema");
							}
						}
					}
					while (rsTable_tDBOutput_4.next()) {
						String table_tDBOutput_4 = rsTable_tDBOutput_4.getString("TABLE_NAME");
						String schema_tDBOutput_4 = rsTable_tDBOutput_4.getString("TABLE_SCHEM");
						if (table_tDBOutput_4.equals(("date")) && (schema_tDBOutput_4.equals(dbschema_tDBOutput_4)
								|| ((dbschema_tDBOutput_4 == null || dbschema_tDBOutput_4.trim().length() == 0)
										&& defaultSchema_tDBOutput_4.equals(schema_tDBOutput_4)))) {
							whetherExist_tDBOutput_4 = true;
							break;
						}
					}
				}
				if (whetherExist_tDBOutput_4) {
					try (java.sql.Statement stmtDrop_tDBOutput_4 = conn_tDBOutput_4.createStatement()) {
						stmtDrop_tDBOutput_4.execute("DROP TABLE \"" + tableName_tDBOutput_4 + "\"");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_4 = conn_tDBOutput_4.createStatement()) {
					stmtCreate_tDBOutput_4.execute("CREATE TABLE \"" + tableName_tDBOutput_4
							+ "\"(\"ID_date\" INT4  not null ,\"Date\" TIMESTAMP(29)  ,\"Annee\" VARCHAR(4)  ,\"Mois\" VARCHAR(2)  ,\"Semaine\" VARCHAR(2)  ,\"Saison\" VARCHAR(50)  ,primary key(\"ID_date\"))");
				}
				java.sql.PreparedStatement pstmt_tDBOutput_4 = conn_tDBOutput_4.prepareStatement(
						"SELECT COUNT(1) FROM \"" + tableName_tDBOutput_4 + "\" WHERE \"ID_date\" = ?");
				resourceMap.put("pstmt_tDBOutput_4", pstmt_tDBOutput_4);
				String insert_tDBOutput_4 = "INSERT INTO \"" + tableName_tDBOutput_4
						+ "\" (\"ID_date\",\"Date\",\"Annee\",\"Mois\",\"Semaine\",\"Saison\") VALUES (?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_4 = conn_tDBOutput_4
						.prepareStatement(insert_tDBOutput_4);
				resourceMap.put("pstmtInsert_tDBOutput_4", pstmtInsert_tDBOutput_4);
				String update_tDBOutput_4 = "UPDATE \"" + tableName_tDBOutput_4
						+ "\" SET \"Date\" = ?,\"Annee\" = ?,\"Mois\" = ?,\"Semaine\" = ?,\"Saison\" = ? WHERE \"ID_date\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_4 = conn_tDBOutput_4
						.prepareStatement(update_tDBOutput_4);
				resourceMap.put("pstmtUpdate_tDBOutput_4", pstmtUpdate_tDBOutput_4);

				/**
				 * [tDBOutput_4 begin ] stop
				 */

				/**
				 * [tDBOutput_5 begin ] start
				 */

				ok_Hash.put("tDBOutput_5", false);
				start_Hash.put("tDBOutput_5", System.currentTimeMillis());

				currentComponent = "tDBOutput_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "cube_lieu");
				}

				int tos_count_tDBOutput_5 = 0;

				String dbschema_tDBOutput_5 = null;
				dbschema_tDBOutput_5 = "public";

				String tableName_tDBOutput_5 = null;
				if (dbschema_tDBOutput_5 == null || dbschema_tDBOutput_5.trim().length() == 0) {
					tableName_tDBOutput_5 = ("lieu");
				} else {
					tableName_tDBOutput_5 = dbschema_tDBOutput_5 + "\".\"" + ("lieu");
				}

				int updateKeyCount_tDBOutput_5 = 1;
				if (updateKeyCount_tDBOutput_5 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_5 == 5 && true) {
					System.err.println("For update, every Schema column can not be a key");
				}

				int nb_line_tDBOutput_5 = 0;
				int nb_line_update_tDBOutput_5 = 0;
				int nb_line_inserted_tDBOutput_5 = 0;
				int nb_line_deleted_tDBOutput_5 = 0;
				int nb_line_rejected_tDBOutput_5 = 0;

				int deletedCount_tDBOutput_5 = 0;
				int updatedCount_tDBOutput_5 = 0;
				int insertedCount_tDBOutput_5 = 0;
				int rowsToCommitCount_tDBOutput_5 = 0;
				int rejectedCount_tDBOutput_5 = 0;

				boolean whetherReject_tDBOutput_5 = false;

				java.sql.Connection conn_tDBOutput_5 = null;
				String dbUser_tDBOutput_5 = null;

				java.lang.Class.forName("org.postgresql.Driver");

				String url_tDBOutput_5 = "jdbc:postgresql://" + "localhost" + ":" + "5432" + "/" + "dimension_db";
				dbUser_tDBOutput_5 = "postgres";

				final String decryptedPassword_tDBOutput_5 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:Y1zZcpDCdCfXx2kf+5aks8kNvVjtncRqgnYvU3SKcNHci/Ttfg==");

				String dbPwd_tDBOutput_5 = decryptedPassword_tDBOutput_5;

				conn_tDBOutput_5 = java.sql.DriverManager.getConnection(url_tDBOutput_5, dbUser_tDBOutput_5,
						dbPwd_tDBOutput_5);

				resourceMap.put("conn_tDBOutput_5", conn_tDBOutput_5);
				conn_tDBOutput_5.setAutoCommit(false);
				int commitEvery_tDBOutput_5 = 10000;
				int commitCounter_tDBOutput_5 = 0;

				int count_tDBOutput_5 = 0;
				java.sql.DatabaseMetaData dbMetaData_tDBOutput_5 = conn_tDBOutput_5.getMetaData();
				boolean whetherExist_tDBOutput_5 = false;
				try (java.sql.ResultSet rsTable_tDBOutput_5 = dbMetaData_tDBOutput_5.getTables(null, null, null,
						new String[] { "TABLE" })) {
					String defaultSchema_tDBOutput_5 = "public";
					if (dbschema_tDBOutput_5 == null || dbschema_tDBOutput_5.trim().length() == 0) {
						try (java.sql.Statement stmtSchema_tDBOutput_5 = conn_tDBOutput_5.createStatement();
								java.sql.ResultSet rsSchema_tDBOutput_5 = stmtSchema_tDBOutput_5
										.executeQuery("select current_schema() ")) {
							while (rsSchema_tDBOutput_5.next()) {
								defaultSchema_tDBOutput_5 = rsSchema_tDBOutput_5.getString("current_schema");
							}
						}
					}
					while (rsTable_tDBOutput_5.next()) {
						String table_tDBOutput_5 = rsTable_tDBOutput_5.getString("TABLE_NAME");
						String schema_tDBOutput_5 = rsTable_tDBOutput_5.getString("TABLE_SCHEM");
						if (table_tDBOutput_5.equals(("lieu")) && (schema_tDBOutput_5.equals(dbschema_tDBOutput_5)
								|| ((dbschema_tDBOutput_5 == null || dbschema_tDBOutput_5.trim().length() == 0)
										&& defaultSchema_tDBOutput_5.equals(schema_tDBOutput_5)))) {
							whetherExist_tDBOutput_5 = true;
							break;
						}
					}
				}
				if (whetherExist_tDBOutput_5) {
					try (java.sql.Statement stmtDrop_tDBOutput_5 = conn_tDBOutput_5.createStatement()) {
						stmtDrop_tDBOutput_5.execute("DROP TABLE \"" + tableName_tDBOutput_5 + "\"");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_5 = conn_tDBOutput_5.createStatement()) {
					stmtCreate_tDBOutput_5.execute("CREATE TABLE \"" + tableName_tDBOutput_5
							+ "\"(\"ID_lieu\" INT4  not null ,\"Ville\" VARCHAR(50)  ,\"Pays\" VARCHAR(50)  ,\"Continent\" VARCHAR(50)  ,\"Region\" VARCHAR(50)  ,primary key(\"ID_lieu\"))");
				}
				java.sql.PreparedStatement pstmt_tDBOutput_5 = conn_tDBOutput_5.prepareStatement(
						"SELECT COUNT(1) FROM \"" + tableName_tDBOutput_5 + "\" WHERE \"ID_lieu\" = ?");
				resourceMap.put("pstmt_tDBOutput_5", pstmt_tDBOutput_5);
				String insert_tDBOutput_5 = "INSERT INTO \"" + tableName_tDBOutput_5
						+ "\" (\"ID_lieu\",\"Ville\",\"Pays\",\"Continent\",\"Region\") VALUES (?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_5 = conn_tDBOutput_5
						.prepareStatement(insert_tDBOutput_5);
				resourceMap.put("pstmtInsert_tDBOutput_5", pstmtInsert_tDBOutput_5);
				String update_tDBOutput_5 = "UPDATE \"" + tableName_tDBOutput_5
						+ "\" SET \"Ville\" = ?,\"Pays\" = ?,\"Continent\" = ?,\"Region\" = ? WHERE \"ID_lieu\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_5 = conn_tDBOutput_5
						.prepareStatement(update_tDBOutput_5);
				resourceMap.put("pstmtUpdate_tDBOutput_5", pstmtUpdate_tDBOutput_5);

				/**
				 * [tDBOutput_5 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				cube_commandeStruct cube_commande_tmp = new cube_commandeStruct();
				cube_clientStruct cube_client_tmp = new cube_clientStruct();
				cube_produitStruct cube_produit_tmp = new cube_produitStruct();
				cube_dateStruct cube_date_tmp = new cube_dateStruct();
				cube_lieuStruct cube_lieu_tmp = new cube_lieuStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
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
						"enc:routine.encryption.key.v1:9EkZU0yb+UVpdoRe138ps+fST6rPt09X3zvAvanG4lyh+4BxAg==");

				String dbPwd_tDBInput_1 = decryptedPassword_tDBInput_1;

				String url_tDBInput_1 = "jdbc:postgresql://" + "localhost" + ":" + "5432" + "/" + "aw_datawarehouse";

				conn_tDBInput_1 = java.sql.DriverManager.getConnection(url_tDBInput_1, dbUser_tDBInput_1,
						dbPwd_tDBInput_1);

				conn_tDBInput_1.setAutoCommit(false);

				java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

				String dbquery_tDBInput_1 = "SELECT \n  \"public\".\"data\".\"ID\", \n  \"public\".\"data\".\"Quantite Commande\", \n  \"public\".\"data\".\"Cout stand"
						+ "ard\", \n  \"public\".\"data\".\"Nom Client\", \n  \"public\".\"data\".\"Ville\", \n  \"public\".\"data\".\"Nom Produit\", "
						+ "\n  \"public\".\"data\".\"Couleur\", \n  \"public\".\"data\".\"Prix\", \n  \"public\".\"data\".\"Modele\", \n  \"public\".\""
						+ "data\".\"Sous Categorie\", \n  \"public\".\"data\".\"Categorie\", \n  \"public\".\"data\".\"Type Business\", \n  \"public\""
						+ ".\"data\".\"Type Canal\", \n  \"public\".\"data\".\"Region\", \n  \"public\".\"data\".\"Pays\", \n  \"public\".\"data\".\"C"
						+ "ontinent\", \n  \"public\".\"data\".\"Date\", \n  \"public\".\"data\".\"Etat Civil\", \n  \"public\".\"data\".\"Genre\", \n "
						+ " \"public\".\"data\".\"Revenu annuel\", \n  \"public\".\"data\".\"Nbr Enfants\", \n  \"public\".\"data\".\"Poste\", \n  \"p"
						+ "ublic\".\"data\".\"Propeietaire Maison\", \n  \"public\".\"data\".\"Nbr Voitures\", \n  \"public\".\"data\".\"Annee\", \n  "
						+ "\"public\".\"data\".\"Mois\", \n  \"public\".\"data\".\"Semaine\", \n  \"public\".\"data\".\"Saison\", \n  \"public\".\"dat"
						+ "a\".\"Age\", \n  \"public\".\"data\".\"Tranche d'âge\"\nFROM \"public\".\"data\"";

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
							row1.ID = 0;
						} else {

							row1.ID = rs_tDBInput_1.getInt(1);
							if (rs_tDBInput_1.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_1 < 2) {
							row1.Quantite_Commande = null;
						} else {

							row1.Quantite_Commande = rs_tDBInput_1.getInt(2);
							if (rs_tDBInput_1.wasNull()) {
								row1.Quantite_Commande = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 3) {
							row1.Cout_standard = null;
						} else {

							row1.Cout_standard = rs_tDBInput_1.getFloat(3);
							if (rs_tDBInput_1.wasNull()) {
								row1.Cout_standard = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 4) {
							row1.Nom_Client = null;
						} else {

							row1.Nom_Client = routines.system.JDBCUtil.getString(rs_tDBInput_1, 4, false);
						}
						if (colQtyInRs_tDBInput_1 < 5) {
							row1.Ville = null;
						} else {

							row1.Ville = routines.system.JDBCUtil.getString(rs_tDBInput_1, 5, false);
						}
						if (colQtyInRs_tDBInput_1 < 6) {
							row1.Nom_Produit = null;
						} else {

							row1.Nom_Produit = routines.system.JDBCUtil.getString(rs_tDBInput_1, 6, false);
						}
						if (colQtyInRs_tDBInput_1 < 7) {
							row1.Couleur = null;
						} else {

							row1.Couleur = routines.system.JDBCUtil.getString(rs_tDBInput_1, 7, false);
						}
						if (colQtyInRs_tDBInput_1 < 8) {
							row1.Prix = null;
						} else {

							row1.Prix = rs_tDBInput_1.getFloat(8);
							if (rs_tDBInput_1.wasNull()) {
								row1.Prix = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 9) {
							row1.Modele = null;
						} else {

							row1.Modele = routines.system.JDBCUtil.getString(rs_tDBInput_1, 9, false);
						}
						if (colQtyInRs_tDBInput_1 < 10) {
							row1.Sous_Categorie = null;
						} else {

							row1.Sous_Categorie = routines.system.JDBCUtil.getString(rs_tDBInput_1, 10, false);
						}
						if (colQtyInRs_tDBInput_1 < 11) {
							row1.Categorie = null;
						} else {

							row1.Categorie = routines.system.JDBCUtil.getString(rs_tDBInput_1, 11, false);
						}
						if (colQtyInRs_tDBInput_1 < 12) {
							row1.Type_Business = null;
						} else {

							row1.Type_Business = routines.system.JDBCUtil.getString(rs_tDBInput_1, 12, false);
						}
						if (colQtyInRs_tDBInput_1 < 13) {
							row1.Type_Canal = null;
						} else {

							row1.Type_Canal = routines.system.JDBCUtil.getString(rs_tDBInput_1, 13, false);
						}
						if (colQtyInRs_tDBInput_1 < 14) {
							row1.Region = null;
						} else {

							row1.Region = routines.system.JDBCUtil.getString(rs_tDBInput_1, 14, false);
						}
						if (colQtyInRs_tDBInput_1 < 15) {
							row1.Pays = null;
						} else {

							row1.Pays = routines.system.JDBCUtil.getString(rs_tDBInput_1, 15, false);
						}
						if (colQtyInRs_tDBInput_1 < 16) {
							row1.Continent = null;
						} else {

							row1.Continent = routines.system.JDBCUtil.getString(rs_tDBInput_1, 16, false);
						}
						if (colQtyInRs_tDBInput_1 < 17) {
							row1.Date = null;
						} else {

							row1.Date = routines.system.JDBCUtil.getDate(rs_tDBInput_1, 17);
						}
						if (colQtyInRs_tDBInput_1 < 18) {
							row1.Etat_Civil = null;
						} else {

							row1.Etat_Civil = routines.system.JDBCUtil.getString(rs_tDBInput_1, 18, false);
						}
						if (colQtyInRs_tDBInput_1 < 19) {
							row1.Genre = null;
						} else {

							row1.Genre = routines.system.JDBCUtil.getString(rs_tDBInput_1, 19, false);
						}
						if (colQtyInRs_tDBInput_1 < 20) {
							row1.Revenu_annuel = null;
						} else {

							row1.Revenu_annuel = rs_tDBInput_1.getFloat(20);
							if (rs_tDBInput_1.wasNull()) {
								row1.Revenu_annuel = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 21) {
							row1.Nbr_Enfants = null;
						} else {

							row1.Nbr_Enfants = rs_tDBInput_1.getInt(21);
							if (rs_tDBInput_1.wasNull()) {
								row1.Nbr_Enfants = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 22) {
							row1.Poste = null;
						} else {

							row1.Poste = routines.system.JDBCUtil.getString(rs_tDBInput_1, 22, false);
						}
						if (colQtyInRs_tDBInput_1 < 23) {
							row1.Propeietaire_Maison = null;
						} else {

							row1.Propeietaire_Maison = routines.system.JDBCUtil.getString(rs_tDBInput_1, 23, false);
						}
						if (colQtyInRs_tDBInput_1 < 24) {
							row1.Nbr_Voitures = null;
						} else {

							row1.Nbr_Voitures = rs_tDBInput_1.getInt(24);
							if (rs_tDBInput_1.wasNull()) {
								row1.Nbr_Voitures = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 25) {
							row1.Annee = null;
						} else {

							row1.Annee = routines.system.JDBCUtil.getString(rs_tDBInput_1, 25, false);
						}
						if (colQtyInRs_tDBInput_1 < 26) {
							row1.Mois = null;
						} else {

							row1.Mois = routines.system.JDBCUtil.getString(rs_tDBInput_1, 26, false);
						}
						if (colQtyInRs_tDBInput_1 < 27) {
							row1.Semaine = null;
						} else {

							row1.Semaine = routines.system.JDBCUtil.getString(rs_tDBInput_1, 27, false);
						}
						if (colQtyInRs_tDBInput_1 < 28) {
							row1.Saison = null;
						} else {

							row1.Saison = routines.system.JDBCUtil.getString(rs_tDBInput_1, 28, false);
						}
						if (colQtyInRs_tDBInput_1 < 29) {
							row1.Age = null;
						} else {

							row1.Age = rs_tDBInput_1.getInt(29);
							if (rs_tDBInput_1.wasNull()) {
								row1.Age = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 30) {
							row1.Tranche_d_age = null;
						} else {

							row1.Tranche_d_age = routines.system.JDBCUtil.getString(rs_tDBInput_1, 30, false);
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
						 * [tMap_1 main ] start
						 */

						currentComponent = "tMap_1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row1"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_1 = false;
						boolean mainRowRejected_tMap_1 = false;

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
							// ###############################
							// # Output tables

							cube_commande = null;
							cube_client = null;
							cube_produit = null;
							cube_date = null;
							cube_lieu = null;

// # Output table : 'cube_commande'
							cube_commande_tmp.ID_commande = Numeric.sequence("s1", 1, 1);
							cube_commande_tmp.Quantite_Commande = row1.Quantite_Commande;
							cube_commande_tmp.Type_Business = row1.Type_Business;
							cube_commande_tmp.Type_Canal = row1.Type_Canal;
							cube_commande = cube_commande_tmp;

// # Output table : 'cube_client'
							cube_client_tmp.ID_client = Numeric.sequence("s2", 1, 1);
							cube_client_tmp.Nom_Client = row1.Nom_Client;
							cube_client_tmp.Genre = row1.Genre;
							cube_client_tmp.Etat_Civil = row1.Etat_Civil;
							cube_client_tmp.Revenu_annuel = row1.Revenu_annuel;
							cube_client_tmp.Poste = row1.Poste;
							cube_client_tmp.Nbr_Enfants = row1.Nbr_Enfants;
							cube_client_tmp.Propeietaire_Maison = row1.Propeietaire_Maison;
							cube_client_tmp.Nbr_Voitures = row1.Nbr_Voitures;
							cube_client_tmp.Age = row1.Age;
							cube_client_tmp.Tranche_d_age = row1.Tranche_d_age;
							cube_client = cube_client_tmp;

// # Output table : 'cube_produit'
							cube_produit_tmp.ID_produit = Numeric.sequence("s3", 1, 1);
							cube_produit_tmp.Nom_Produit = row1.Nom_Produit;
							cube_produit_tmp.Cout_standard = row1.Cout_standard;
							cube_produit_tmp.Prix = row1.Prix;
							cube_produit_tmp.Couleur = row1.Couleur;
							cube_produit_tmp.Modele = row1.Modele;
							cube_produit_tmp.Categorie = row1.Categorie;
							cube_produit_tmp.Sous_Categorie = row1.Sous_Categorie;
							cube_produit = cube_produit_tmp;

// # Output table : 'cube_date'
							cube_date_tmp.ID_date = Numeric.sequence("s4", 1, 1);
							cube_date_tmp.Date = row1.Date;
							cube_date_tmp.Annee = row1.Annee;
							cube_date_tmp.Mois = row1.Mois;
							cube_date_tmp.Semaine = row1.Semaine;
							cube_date_tmp.Saison = row1.Saison;
							cube_date = cube_date_tmp;

// # Output table : 'cube_lieu'
							cube_lieu_tmp.ID_lieu = Numeric.sequence("s5", 1, 1);
							cube_lieu_tmp.Ville = row1.Ville;
							cube_lieu_tmp.Pays = row1.Pays;
							cube_lieu_tmp.Continent = row1.Continent;
							cube_lieu_tmp.Region = row1.Region;
							cube_lieu = cube_lieu_tmp;
// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_1 = false;

						tos_count_tMap_1++;

						/**
						 * [tMap_1 main ] stop
						 */

						/**
						 * [tMap_1 process_data_begin ] start
						 */

						currentComponent = "tMap_1";

						/**
						 * [tMap_1 process_data_begin ] stop
						 */
// Start of branch "cube_commande"
						if (cube_commande != null) {

							/**
							 * [tDBOutput_1 main ] start
							 */

							currentComponent = "tDBOutput_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "cube_commande"

								);
							}

							whetherReject_tDBOutput_1 = false;
							pstmt_tDBOutput_1.setInt(1, cube_commande.ID_commande);

							int checkCount_tDBOutput_1 = -1;
							try (java.sql.ResultSet rs_tDBOutput_1 = pstmt_tDBOutput_1.executeQuery()) {
								while (rs_tDBOutput_1.next()) {
									checkCount_tDBOutput_1 = rs_tDBOutput_1.getInt(1);
								}
							}
							if (checkCount_tDBOutput_1 > 0) {
								if (cube_commande.Quantite_Commande == null) {
									pstmtUpdate_tDBOutput_1.setNull(1, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_1.setInt(1, cube_commande.Quantite_Commande);
								}

								if (cube_commande.Type_Business == null) {
									pstmtUpdate_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(2, cube_commande.Type_Business);
								}

								if (cube_commande.Type_Canal == null) {
									pstmtUpdate_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(3, cube_commande.Type_Canal);
								}

								pstmtUpdate_tDBOutput_1.setInt(4 + count_tDBOutput_1, cube_commande.ID_commande);

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
								pstmtInsert_tDBOutput_1.setInt(1, cube_commande.ID_commande);

								if (cube_commande.Quantite_Commande == null) {
									pstmtInsert_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_1.setInt(2, cube_commande.Quantite_Commande);
								}

								if (cube_commande.Type_Business == null) {
									pstmtInsert_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(3, cube_commande.Type_Business);
								}

								if (cube_commande.Type_Canal == null) {
									pstmtInsert_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_1.setString(4, cube_commande.Type_Canal);
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

						} // End of branch "cube_commande"

// Start of branch "cube_client"
						if (cube_client != null) {

							/**
							 * [tDBOutput_2 main ] start
							 */

							currentComponent = "tDBOutput_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "cube_client"

								);
							}

							whetherReject_tDBOutput_2 = false;
							pstmt_tDBOutput_2.setInt(1, cube_client.ID_client);

							int checkCount_tDBOutput_2 = -1;
							try (java.sql.ResultSet rs_tDBOutput_2 = pstmt_tDBOutput_2.executeQuery()) {
								while (rs_tDBOutput_2.next()) {
									checkCount_tDBOutput_2 = rs_tDBOutput_2.getInt(1);
								}
							}
							if (checkCount_tDBOutput_2 > 0) {
								if (cube_client.Nom_Client == null) {
									pstmtUpdate_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(1, cube_client.Nom_Client);
								}

								if (cube_client.Genre == null) {
									pstmtUpdate_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(2, cube_client.Genre);
								}

								if (cube_client.Etat_Civil == null) {
									pstmtUpdate_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(3, cube_client.Etat_Civil);
								}

								if (cube_client.Revenu_annuel == null) {
									pstmtUpdate_tDBOutput_2.setNull(4, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_2.setFloat(4, cube_client.Revenu_annuel);
								}

								if (cube_client.Poste == null) {
									pstmtUpdate_tDBOutput_2.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(5, cube_client.Poste);
								}

								if (cube_client.Nbr_Enfants == null) {
									pstmtUpdate_tDBOutput_2.setNull(6, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_2.setInt(6, cube_client.Nbr_Enfants);
								}

								if (cube_client.Propeietaire_Maison == null) {
									pstmtUpdate_tDBOutput_2.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(7, cube_client.Propeietaire_Maison);
								}

								if (cube_client.Nbr_Voitures == null) {
									pstmtUpdate_tDBOutput_2.setNull(8, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_2.setInt(8, cube_client.Nbr_Voitures);
								}

								if (cube_client.Age == null) {
									pstmtUpdate_tDBOutput_2.setNull(9, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_2.setInt(9, cube_client.Age);
								}

								if (cube_client.Tranche_d_age == null) {
									pstmtUpdate_tDBOutput_2.setNull(10, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(10, cube_client.Tranche_d_age);
								}

								pstmtUpdate_tDBOutput_2.setInt(11 + count_tDBOutput_2, cube_client.ID_client);

								try {

									int processedCount_tDBOutput_2 = pstmtUpdate_tDBOutput_2.executeUpdate();
									updatedCount_tDBOutput_2 += processedCount_tDBOutput_2;
									rowsToCommitCount_tDBOutput_2 += processedCount_tDBOutput_2;
									nb_line_tDBOutput_2++;

								} catch (java.lang.Exception e) {
									globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

									whetherReject_tDBOutput_2 = true;
									nb_line_tDBOutput_2++;
									System.err.print(e.getMessage());
								}
							} else {
								pstmtInsert_tDBOutput_2.setInt(1, cube_client.ID_client);

								if (cube_client.Nom_Client == null) {
									pstmtInsert_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(2, cube_client.Nom_Client);
								}

								if (cube_client.Genre == null) {
									pstmtInsert_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(3, cube_client.Genre);
								}

								if (cube_client.Etat_Civil == null) {
									pstmtInsert_tDBOutput_2.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(4, cube_client.Etat_Civil);
								}

								if (cube_client.Revenu_annuel == null) {
									pstmtInsert_tDBOutput_2.setNull(5, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_2.setFloat(5, cube_client.Revenu_annuel);
								}

								if (cube_client.Poste == null) {
									pstmtInsert_tDBOutput_2.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(6, cube_client.Poste);
								}

								if (cube_client.Nbr_Enfants == null) {
									pstmtInsert_tDBOutput_2.setNull(7, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_2.setInt(7, cube_client.Nbr_Enfants);
								}

								if (cube_client.Propeietaire_Maison == null) {
									pstmtInsert_tDBOutput_2.setNull(8, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(8, cube_client.Propeietaire_Maison);
								}

								if (cube_client.Nbr_Voitures == null) {
									pstmtInsert_tDBOutput_2.setNull(9, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_2.setInt(9, cube_client.Nbr_Voitures);
								}

								if (cube_client.Age == null) {
									pstmtInsert_tDBOutput_2.setNull(10, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_2.setInt(10, cube_client.Age);
								}

								if (cube_client.Tranche_d_age == null) {
									pstmtInsert_tDBOutput_2.setNull(11, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(11, cube_client.Tranche_d_age);
								}

								try {

									int processedCount_tDBOutput_2 = pstmtInsert_tDBOutput_2.executeUpdate();
									insertedCount_tDBOutput_2 += processedCount_tDBOutput_2;
									rowsToCommitCount_tDBOutput_2 += processedCount_tDBOutput_2;
									nb_line_tDBOutput_2++;

								} catch (java.lang.Exception e) {
									globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

									whetherReject_tDBOutput_2 = true;
									nb_line_tDBOutput_2++;
									System.err.print(e.getMessage());
								}
							}
							commitCounter_tDBOutput_2++;
							if (commitEvery_tDBOutput_2 <= commitCounter_tDBOutput_2) {
								if (rowsToCommitCount_tDBOutput_2 != 0) {

								}
								conn_tDBOutput_2.commit();
								if (rowsToCommitCount_tDBOutput_2 != 0) {

									rowsToCommitCount_tDBOutput_2 = 0;
								}
								commitCounter_tDBOutput_2 = 0;
							}

							tos_count_tDBOutput_2++;

							/**
							 * [tDBOutput_2 main ] stop
							 */

							/**
							 * [tDBOutput_2 process_data_begin ] start
							 */

							currentComponent = "tDBOutput_2";

							/**
							 * [tDBOutput_2 process_data_begin ] stop
							 */

							/**
							 * [tDBOutput_2 process_data_end ] start
							 */

							currentComponent = "tDBOutput_2";

							/**
							 * [tDBOutput_2 process_data_end ] stop
							 */

						} // End of branch "cube_client"

// Start of branch "cube_produit"
						if (cube_produit != null) {

							/**
							 * [tDBOutput_3 main ] start
							 */

							currentComponent = "tDBOutput_3";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "cube_produit"

								);
							}

							whetherReject_tDBOutput_3 = false;
							pstmt_tDBOutput_3.setInt(1, cube_produit.ID_produit);

							int checkCount_tDBOutput_3 = -1;
							try (java.sql.ResultSet rs_tDBOutput_3 = pstmt_tDBOutput_3.executeQuery()) {
								while (rs_tDBOutput_3.next()) {
									checkCount_tDBOutput_3 = rs_tDBOutput_3.getInt(1);
								}
							}
							if (checkCount_tDBOutput_3 > 0) {
								if (cube_produit.Nom_Produit == null) {
									pstmtUpdate_tDBOutput_3.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(1, cube_produit.Nom_Produit);
								}

								if (cube_produit.Cout_standard == null) {
									pstmtUpdate_tDBOutput_3.setNull(2, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_3.setFloat(2, cube_produit.Cout_standard);
								}

								if (cube_produit.Prix == null) {
									pstmtUpdate_tDBOutput_3.setNull(3, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_3.setFloat(3, cube_produit.Prix);
								}

								if (cube_produit.Couleur == null) {
									pstmtUpdate_tDBOutput_3.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(4, cube_produit.Couleur);
								}

								if (cube_produit.Modele == null) {
									pstmtUpdate_tDBOutput_3.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(5, cube_produit.Modele);
								}

								if (cube_produit.Categorie == null) {
									pstmtUpdate_tDBOutput_3.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(6, cube_produit.Categorie);
								}

								if (cube_produit.Sous_Categorie == null) {
									pstmtUpdate_tDBOutput_3.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(7, cube_produit.Sous_Categorie);
								}

								pstmtUpdate_tDBOutput_3.setInt(8 + count_tDBOutput_3, cube_produit.ID_produit);

								try {

									int processedCount_tDBOutput_3 = pstmtUpdate_tDBOutput_3.executeUpdate();
									updatedCount_tDBOutput_3 += processedCount_tDBOutput_3;
									rowsToCommitCount_tDBOutput_3 += processedCount_tDBOutput_3;
									nb_line_tDBOutput_3++;

								} catch (java.lang.Exception e) {
									globalMap.put("tDBOutput_3_ERROR_MESSAGE", e.getMessage());

									whetherReject_tDBOutput_3 = true;
									nb_line_tDBOutput_3++;
									System.err.print(e.getMessage());
								}
							} else {
								pstmtInsert_tDBOutput_3.setInt(1, cube_produit.ID_produit);

								if (cube_produit.Nom_Produit == null) {
									pstmtInsert_tDBOutput_3.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_3.setString(2, cube_produit.Nom_Produit);
								}

								if (cube_produit.Cout_standard == null) {
									pstmtInsert_tDBOutput_3.setNull(3, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_3.setFloat(3, cube_produit.Cout_standard);
								}

								if (cube_produit.Prix == null) {
									pstmtInsert_tDBOutput_3.setNull(4, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_3.setFloat(4, cube_produit.Prix);
								}

								if (cube_produit.Couleur == null) {
									pstmtInsert_tDBOutput_3.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_3.setString(5, cube_produit.Couleur);
								}

								if (cube_produit.Modele == null) {
									pstmtInsert_tDBOutput_3.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_3.setString(6, cube_produit.Modele);
								}

								if (cube_produit.Categorie == null) {
									pstmtInsert_tDBOutput_3.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_3.setString(7, cube_produit.Categorie);
								}

								if (cube_produit.Sous_Categorie == null) {
									pstmtInsert_tDBOutput_3.setNull(8, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_3.setString(8, cube_produit.Sous_Categorie);
								}

								try {

									int processedCount_tDBOutput_3 = pstmtInsert_tDBOutput_3.executeUpdate();
									insertedCount_tDBOutput_3 += processedCount_tDBOutput_3;
									rowsToCommitCount_tDBOutput_3 += processedCount_tDBOutput_3;
									nb_line_tDBOutput_3++;

								} catch (java.lang.Exception e) {
									globalMap.put("tDBOutput_3_ERROR_MESSAGE", e.getMessage());

									whetherReject_tDBOutput_3 = true;
									nb_line_tDBOutput_3++;
									System.err.print(e.getMessage());
								}
							}
							commitCounter_tDBOutput_3++;
							if (commitEvery_tDBOutput_3 <= commitCounter_tDBOutput_3) {
								if (rowsToCommitCount_tDBOutput_3 != 0) {

								}
								conn_tDBOutput_3.commit();
								if (rowsToCommitCount_tDBOutput_3 != 0) {

									rowsToCommitCount_tDBOutput_3 = 0;
								}
								commitCounter_tDBOutput_3 = 0;
							}

							tos_count_tDBOutput_3++;

							/**
							 * [tDBOutput_3 main ] stop
							 */

							/**
							 * [tDBOutput_3 process_data_begin ] start
							 */

							currentComponent = "tDBOutput_3";

							/**
							 * [tDBOutput_3 process_data_begin ] stop
							 */

							/**
							 * [tDBOutput_3 process_data_end ] start
							 */

							currentComponent = "tDBOutput_3";

							/**
							 * [tDBOutput_3 process_data_end ] stop
							 */

						} // End of branch "cube_produit"

// Start of branch "cube_date"
						if (cube_date != null) {

							/**
							 * [tDBOutput_4 main ] start
							 */

							currentComponent = "tDBOutput_4";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "cube_date"

								);
							}

							whetherReject_tDBOutput_4 = false;
							pstmt_tDBOutput_4.setInt(1, cube_date.ID_date);

							int checkCount_tDBOutput_4 = -1;
							try (java.sql.ResultSet rs_tDBOutput_4 = pstmt_tDBOutput_4.executeQuery()) {
								while (rs_tDBOutput_4.next()) {
									checkCount_tDBOutput_4 = rs_tDBOutput_4.getInt(1);
								}
							}
							if (checkCount_tDBOutput_4 > 0) {
								if (cube_date.Date != null) {
									pstmtUpdate_tDBOutput_4.setTimestamp(1,
											new java.sql.Timestamp(cube_date.Date.getTime()));
								} else {
									pstmtUpdate_tDBOutput_4.setNull(1, java.sql.Types.TIMESTAMP);
								}

								if (cube_date.Annee == null) {
									pstmtUpdate_tDBOutput_4.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_4.setString(2, cube_date.Annee);
								}

								if (cube_date.Mois == null) {
									pstmtUpdate_tDBOutput_4.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_4.setString(3, cube_date.Mois);
								}

								if (cube_date.Semaine == null) {
									pstmtUpdate_tDBOutput_4.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_4.setString(4, cube_date.Semaine);
								}

								if (cube_date.Saison == null) {
									pstmtUpdate_tDBOutput_4.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_4.setString(5, cube_date.Saison);
								}

								pstmtUpdate_tDBOutput_4.setInt(6 + count_tDBOutput_4, cube_date.ID_date);

								try {

									int processedCount_tDBOutput_4 = pstmtUpdate_tDBOutput_4.executeUpdate();
									updatedCount_tDBOutput_4 += processedCount_tDBOutput_4;
									rowsToCommitCount_tDBOutput_4 += processedCount_tDBOutput_4;
									nb_line_tDBOutput_4++;

								} catch (java.lang.Exception e) {
									globalMap.put("tDBOutput_4_ERROR_MESSAGE", e.getMessage());

									whetherReject_tDBOutput_4 = true;
									nb_line_tDBOutput_4++;
									System.err.print(e.getMessage());
								}
							} else {
								pstmtInsert_tDBOutput_4.setInt(1, cube_date.ID_date);

								if (cube_date.Date != null) {
									pstmtInsert_tDBOutput_4.setTimestamp(2,
											new java.sql.Timestamp(cube_date.Date.getTime()));
								} else {
									pstmtInsert_tDBOutput_4.setNull(2, java.sql.Types.TIMESTAMP);
								}

								if (cube_date.Annee == null) {
									pstmtInsert_tDBOutput_4.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_4.setString(3, cube_date.Annee);
								}

								if (cube_date.Mois == null) {
									pstmtInsert_tDBOutput_4.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_4.setString(4, cube_date.Mois);
								}

								if (cube_date.Semaine == null) {
									pstmtInsert_tDBOutput_4.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_4.setString(5, cube_date.Semaine);
								}

								if (cube_date.Saison == null) {
									pstmtInsert_tDBOutput_4.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_4.setString(6, cube_date.Saison);
								}

								try {

									int processedCount_tDBOutput_4 = pstmtInsert_tDBOutput_4.executeUpdate();
									insertedCount_tDBOutput_4 += processedCount_tDBOutput_4;
									rowsToCommitCount_tDBOutput_4 += processedCount_tDBOutput_4;
									nb_line_tDBOutput_4++;

								} catch (java.lang.Exception e) {
									globalMap.put("tDBOutput_4_ERROR_MESSAGE", e.getMessage());

									whetherReject_tDBOutput_4 = true;
									nb_line_tDBOutput_4++;
									System.err.print(e.getMessage());
								}
							}
							commitCounter_tDBOutput_4++;
							if (commitEvery_tDBOutput_4 <= commitCounter_tDBOutput_4) {
								if (rowsToCommitCount_tDBOutput_4 != 0) {

								}
								conn_tDBOutput_4.commit();
								if (rowsToCommitCount_tDBOutput_4 != 0) {

									rowsToCommitCount_tDBOutput_4 = 0;
								}
								commitCounter_tDBOutput_4 = 0;
							}

							tos_count_tDBOutput_4++;

							/**
							 * [tDBOutput_4 main ] stop
							 */

							/**
							 * [tDBOutput_4 process_data_begin ] start
							 */

							currentComponent = "tDBOutput_4";

							/**
							 * [tDBOutput_4 process_data_begin ] stop
							 */

							/**
							 * [tDBOutput_4 process_data_end ] start
							 */

							currentComponent = "tDBOutput_4";

							/**
							 * [tDBOutput_4 process_data_end ] stop
							 */

						} // End of branch "cube_date"

// Start of branch "cube_lieu"
						if (cube_lieu != null) {

							/**
							 * [tDBOutput_5 main ] start
							 */

							currentComponent = "tDBOutput_5";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "cube_lieu"

								);
							}

							whetherReject_tDBOutput_5 = false;
							pstmt_tDBOutput_5.setInt(1, cube_lieu.ID_lieu);

							int checkCount_tDBOutput_5 = -1;
							try (java.sql.ResultSet rs_tDBOutput_5 = pstmt_tDBOutput_5.executeQuery()) {
								while (rs_tDBOutput_5.next()) {
									checkCount_tDBOutput_5 = rs_tDBOutput_5.getInt(1);
								}
							}
							if (checkCount_tDBOutput_5 > 0) {
								if (cube_lieu.Ville == null) {
									pstmtUpdate_tDBOutput_5.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_5.setString(1, cube_lieu.Ville);
								}

								if (cube_lieu.Pays == null) {
									pstmtUpdate_tDBOutput_5.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_5.setString(2, cube_lieu.Pays);
								}

								if (cube_lieu.Continent == null) {
									pstmtUpdate_tDBOutput_5.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_5.setString(3, cube_lieu.Continent);
								}

								if (cube_lieu.Region == null) {
									pstmtUpdate_tDBOutput_5.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_5.setString(4, cube_lieu.Region);
								}

								pstmtUpdate_tDBOutput_5.setInt(5 + count_tDBOutput_5, cube_lieu.ID_lieu);

								try {

									int processedCount_tDBOutput_5 = pstmtUpdate_tDBOutput_5.executeUpdate();
									updatedCount_tDBOutput_5 += processedCount_tDBOutput_5;
									rowsToCommitCount_tDBOutput_5 += processedCount_tDBOutput_5;
									nb_line_tDBOutput_5++;

								} catch (java.lang.Exception e) {
									globalMap.put("tDBOutput_5_ERROR_MESSAGE", e.getMessage());

									whetherReject_tDBOutput_5 = true;
									nb_line_tDBOutput_5++;
									System.err.print(e.getMessage());
								}
							} else {
								pstmtInsert_tDBOutput_5.setInt(1, cube_lieu.ID_lieu);

								if (cube_lieu.Ville == null) {
									pstmtInsert_tDBOutput_5.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_5.setString(2, cube_lieu.Ville);
								}

								if (cube_lieu.Pays == null) {
									pstmtInsert_tDBOutput_5.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_5.setString(3, cube_lieu.Pays);
								}

								if (cube_lieu.Continent == null) {
									pstmtInsert_tDBOutput_5.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_5.setString(4, cube_lieu.Continent);
								}

								if (cube_lieu.Region == null) {
									pstmtInsert_tDBOutput_5.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_5.setString(5, cube_lieu.Region);
								}

								try {

									int processedCount_tDBOutput_5 = pstmtInsert_tDBOutput_5.executeUpdate();
									insertedCount_tDBOutput_5 += processedCount_tDBOutput_5;
									rowsToCommitCount_tDBOutput_5 += processedCount_tDBOutput_5;
									nb_line_tDBOutput_5++;

								} catch (java.lang.Exception e) {
									globalMap.put("tDBOutput_5_ERROR_MESSAGE", e.getMessage());

									whetherReject_tDBOutput_5 = true;
									nb_line_tDBOutput_5++;
									System.err.print(e.getMessage());
								}
							}
							commitCounter_tDBOutput_5++;
							if (commitEvery_tDBOutput_5 <= commitCounter_tDBOutput_5) {
								if (rowsToCommitCount_tDBOutput_5 != 0) {

								}
								conn_tDBOutput_5.commit();
								if (rowsToCommitCount_tDBOutput_5 != 0) {

									rowsToCommitCount_tDBOutput_5 = 0;
								}
								commitCounter_tDBOutput_5 = 0;
							}

							tos_count_tDBOutput_5++;

							/**
							 * [tDBOutput_5 main ] stop
							 */

							/**
							 * [tDBOutput_5 process_data_begin ] start
							 */

							currentComponent = "tDBOutput_5";

							/**
							 * [tDBOutput_5 process_data_begin ] stop
							 */

							/**
							 * [tDBOutput_5 process_data_end ] start
							 */

							currentComponent = "tDBOutput_5";

							/**
							 * [tDBOutput_5 process_data_end ] stop
							 */

						} // End of branch "cube_lieu"

						/**
						 * [tMap_1 process_data_end ] start
						 */

						currentComponent = "tMap_1";

						/**
						 * [tMap_1 process_data_end ] stop
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
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "cube_commande");
				}

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

				/**
				 * [tDBOutput_2 end ] start
				 */

				currentComponent = "tDBOutput_2";

				if (pstmtUpdate_tDBOutput_2 != null) {
					pstmtUpdate_tDBOutput_2.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_2");
				}
				if (pstmtInsert_tDBOutput_2 != null) {
					pstmtInsert_tDBOutput_2.close();
					resourceMap.remove("pstmtInsert_tDBOutput_2");
				}
				if (pstmt_tDBOutput_2 != null) {
					pstmt_tDBOutput_2.close();
					resourceMap.remove("pstmt_tDBOutput_2");
				}
				resourceMap.put("statementClosed_tDBOutput_2", true);
				if (rowsToCommitCount_tDBOutput_2 != 0) {

				}
				conn_tDBOutput_2.commit();
				if (rowsToCommitCount_tDBOutput_2 != 0) {

					rowsToCommitCount_tDBOutput_2 = 0;
				}
				commitCounter_tDBOutput_2 = 0;

				conn_tDBOutput_2.close();

				resourceMap.put("finish_tDBOutput_2", true);

				nb_line_deleted_tDBOutput_2 = nb_line_deleted_tDBOutput_2 + deletedCount_tDBOutput_2;
				nb_line_update_tDBOutput_2 = nb_line_update_tDBOutput_2 + updatedCount_tDBOutput_2;
				nb_line_inserted_tDBOutput_2 = nb_line_inserted_tDBOutput_2 + insertedCount_tDBOutput_2;
				nb_line_rejected_tDBOutput_2 = nb_line_rejected_tDBOutput_2 + rejectedCount_tDBOutput_2;

				globalMap.put("tDBOutput_2_NB_LINE", nb_line_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_UPDATED", nb_line_update_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_DELETED", nb_line_deleted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_2);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "cube_client");
				}

				ok_Hash.put("tDBOutput_2", true);
				end_Hash.put("tDBOutput_2", System.currentTimeMillis());

				/**
				 * [tDBOutput_2 end ] stop
				 */

				/**
				 * [tDBOutput_3 end ] start
				 */

				currentComponent = "tDBOutput_3";

				if (pstmtUpdate_tDBOutput_3 != null) {
					pstmtUpdate_tDBOutput_3.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_3");
				}
				if (pstmtInsert_tDBOutput_3 != null) {
					pstmtInsert_tDBOutput_3.close();
					resourceMap.remove("pstmtInsert_tDBOutput_3");
				}
				if (pstmt_tDBOutput_3 != null) {
					pstmt_tDBOutput_3.close();
					resourceMap.remove("pstmt_tDBOutput_3");
				}
				resourceMap.put("statementClosed_tDBOutput_3", true);
				if (rowsToCommitCount_tDBOutput_3 != 0) {

				}
				conn_tDBOutput_3.commit();
				if (rowsToCommitCount_tDBOutput_3 != 0) {

					rowsToCommitCount_tDBOutput_3 = 0;
				}
				commitCounter_tDBOutput_3 = 0;

				conn_tDBOutput_3.close();

				resourceMap.put("finish_tDBOutput_3", true);

				nb_line_deleted_tDBOutput_3 = nb_line_deleted_tDBOutput_3 + deletedCount_tDBOutput_3;
				nb_line_update_tDBOutput_3 = nb_line_update_tDBOutput_3 + updatedCount_tDBOutput_3;
				nb_line_inserted_tDBOutput_3 = nb_line_inserted_tDBOutput_3 + insertedCount_tDBOutput_3;
				nb_line_rejected_tDBOutput_3 = nb_line_rejected_tDBOutput_3 + rejectedCount_tDBOutput_3;

				globalMap.put("tDBOutput_3_NB_LINE", nb_line_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_UPDATED", nb_line_update_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_DELETED", nb_line_deleted_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_3);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "cube_produit");
				}

				ok_Hash.put("tDBOutput_3", true);
				end_Hash.put("tDBOutput_3", System.currentTimeMillis());

				/**
				 * [tDBOutput_3 end ] stop
				 */

				/**
				 * [tDBOutput_4 end ] start
				 */

				currentComponent = "tDBOutput_4";

				if (pstmtUpdate_tDBOutput_4 != null) {
					pstmtUpdate_tDBOutput_4.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_4");
				}
				if (pstmtInsert_tDBOutput_4 != null) {
					pstmtInsert_tDBOutput_4.close();
					resourceMap.remove("pstmtInsert_tDBOutput_4");
				}
				if (pstmt_tDBOutput_4 != null) {
					pstmt_tDBOutput_4.close();
					resourceMap.remove("pstmt_tDBOutput_4");
				}
				resourceMap.put("statementClosed_tDBOutput_4", true);
				if (rowsToCommitCount_tDBOutput_4 != 0) {

				}
				conn_tDBOutput_4.commit();
				if (rowsToCommitCount_tDBOutput_4 != 0) {

					rowsToCommitCount_tDBOutput_4 = 0;
				}
				commitCounter_tDBOutput_4 = 0;

				conn_tDBOutput_4.close();

				resourceMap.put("finish_tDBOutput_4", true);

				nb_line_deleted_tDBOutput_4 = nb_line_deleted_tDBOutput_4 + deletedCount_tDBOutput_4;
				nb_line_update_tDBOutput_4 = nb_line_update_tDBOutput_4 + updatedCount_tDBOutput_4;
				nb_line_inserted_tDBOutput_4 = nb_line_inserted_tDBOutput_4 + insertedCount_tDBOutput_4;
				nb_line_rejected_tDBOutput_4 = nb_line_rejected_tDBOutput_4 + rejectedCount_tDBOutput_4;

				globalMap.put("tDBOutput_4_NB_LINE", nb_line_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_UPDATED", nb_line_update_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_DELETED", nb_line_deleted_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_4);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "cube_date");
				}

				ok_Hash.put("tDBOutput_4", true);
				end_Hash.put("tDBOutput_4", System.currentTimeMillis());

				/**
				 * [tDBOutput_4 end ] stop
				 */

				/**
				 * [tDBOutput_5 end ] start
				 */

				currentComponent = "tDBOutput_5";

				if (pstmtUpdate_tDBOutput_5 != null) {
					pstmtUpdate_tDBOutput_5.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_5");
				}
				if (pstmtInsert_tDBOutput_5 != null) {
					pstmtInsert_tDBOutput_5.close();
					resourceMap.remove("pstmtInsert_tDBOutput_5");
				}
				if (pstmt_tDBOutput_5 != null) {
					pstmt_tDBOutput_5.close();
					resourceMap.remove("pstmt_tDBOutput_5");
				}
				resourceMap.put("statementClosed_tDBOutput_5", true);
				if (rowsToCommitCount_tDBOutput_5 != 0) {

				}
				conn_tDBOutput_5.commit();
				if (rowsToCommitCount_tDBOutput_5 != 0) {

					rowsToCommitCount_tDBOutput_5 = 0;
				}
				commitCounter_tDBOutput_5 = 0;

				conn_tDBOutput_5.close();

				resourceMap.put("finish_tDBOutput_5", true);

				nb_line_deleted_tDBOutput_5 = nb_line_deleted_tDBOutput_5 + deletedCount_tDBOutput_5;
				nb_line_update_tDBOutput_5 = nb_line_update_tDBOutput_5 + updatedCount_tDBOutput_5;
				nb_line_inserted_tDBOutput_5 = nb_line_inserted_tDBOutput_5 + insertedCount_tDBOutput_5;
				nb_line_rejected_tDBOutput_5 = nb_line_rejected_tDBOutput_5 + rejectedCount_tDBOutput_5;

				globalMap.put("tDBOutput_5_NB_LINE", nb_line_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_UPDATED", nb_line_update_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_DELETED", nb_line_deleted_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_5);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "cube_lieu");
				}

				ok_Hash.put("tDBOutput_5", true);
				end_Hash.put("tDBOutput_5", System.currentTimeMillis());

				/**
				 * [tDBOutput_5 end ] stop
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
				 * [tDBInput_1 finally ] start
				 */

				currentComponent = "tDBInput_1";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
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

				/**
				 * [tDBOutput_2 finally ] start
				 */

				currentComponent = "tDBOutput_2";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_2") == null) {
						java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_2 = null;
						if ((pstmtUpdateToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtUpdate_tDBOutput_2")) != null) {
							pstmtUpdateToClose_tDBOutput_2.close();
						}
						java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_2 = null;
						if ((pstmtInsertToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtInsert_tDBOutput_2")) != null) {
							pstmtInsertToClose_tDBOutput_2.close();
						}
						java.sql.PreparedStatement pstmtToClose_tDBOutput_2 = null;
						if ((pstmtToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_2")) != null) {
							pstmtToClose_tDBOutput_2.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_2") == null) {
						java.sql.Connection ctn_tDBOutput_2 = null;
						if ((ctn_tDBOutput_2 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_2")) != null) {
							try {
								ctn_tDBOutput_2.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_2) {
								String errorMessage_tDBOutput_2 = "failed to close the connection in tDBOutput_2 :"
										+ sqlEx_tDBOutput_2.getMessage();
								System.err.println(errorMessage_tDBOutput_2);
							}
						}
					}
				}

				/**
				 * [tDBOutput_2 finally ] stop
				 */

				/**
				 * [tDBOutput_3 finally ] start
				 */

				currentComponent = "tDBOutput_3";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_3") == null) {
						java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_3 = null;
						if ((pstmtUpdateToClose_tDBOutput_3 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtUpdate_tDBOutput_3")) != null) {
							pstmtUpdateToClose_tDBOutput_3.close();
						}
						java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_3 = null;
						if ((pstmtInsertToClose_tDBOutput_3 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtInsert_tDBOutput_3")) != null) {
							pstmtInsertToClose_tDBOutput_3.close();
						}
						java.sql.PreparedStatement pstmtToClose_tDBOutput_3 = null;
						if ((pstmtToClose_tDBOutput_3 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_3")) != null) {
							pstmtToClose_tDBOutput_3.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_3") == null) {
						java.sql.Connection ctn_tDBOutput_3 = null;
						if ((ctn_tDBOutput_3 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_3")) != null) {
							try {
								ctn_tDBOutput_3.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_3) {
								String errorMessage_tDBOutput_3 = "failed to close the connection in tDBOutput_3 :"
										+ sqlEx_tDBOutput_3.getMessage();
								System.err.println(errorMessage_tDBOutput_3);
							}
						}
					}
				}

				/**
				 * [tDBOutput_3 finally ] stop
				 */

				/**
				 * [tDBOutput_4 finally ] start
				 */

				currentComponent = "tDBOutput_4";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_4") == null) {
						java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_4 = null;
						if ((pstmtUpdateToClose_tDBOutput_4 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtUpdate_tDBOutput_4")) != null) {
							pstmtUpdateToClose_tDBOutput_4.close();
						}
						java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_4 = null;
						if ((pstmtInsertToClose_tDBOutput_4 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtInsert_tDBOutput_4")) != null) {
							pstmtInsertToClose_tDBOutput_4.close();
						}
						java.sql.PreparedStatement pstmtToClose_tDBOutput_4 = null;
						if ((pstmtToClose_tDBOutput_4 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_4")) != null) {
							pstmtToClose_tDBOutput_4.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_4") == null) {
						java.sql.Connection ctn_tDBOutput_4 = null;
						if ((ctn_tDBOutput_4 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_4")) != null) {
							try {
								ctn_tDBOutput_4.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_4) {
								String errorMessage_tDBOutput_4 = "failed to close the connection in tDBOutput_4 :"
										+ sqlEx_tDBOutput_4.getMessage();
								System.err.println(errorMessage_tDBOutput_4);
							}
						}
					}
				}

				/**
				 * [tDBOutput_4 finally ] stop
				 */

				/**
				 * [tDBOutput_5 finally ] start
				 */

				currentComponent = "tDBOutput_5";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_5") == null) {
						java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_5 = null;
						if ((pstmtUpdateToClose_tDBOutput_5 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtUpdate_tDBOutput_5")) != null) {
							pstmtUpdateToClose_tDBOutput_5.close();
						}
						java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_5 = null;
						if ((pstmtInsertToClose_tDBOutput_5 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtInsert_tDBOutput_5")) != null) {
							pstmtInsertToClose_tDBOutput_5.close();
						}
						java.sql.PreparedStatement pstmtToClose_tDBOutput_5 = null;
						if ((pstmtToClose_tDBOutput_5 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_5")) != null) {
							pstmtToClose_tDBOutput_5.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_5") == null) {
						java.sql.Connection ctn_tDBOutput_5 = null;
						if ((ctn_tDBOutput_5 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_5")) != null) {
							try {
								ctn_tDBOutput_5.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_5) {
								String errorMessage_tDBOutput_5 = "failed to close the connection in tDBOutput_5 :"
										+ sqlEx_tDBOutput_5.getMessage();
								System.err.println(errorMessage_tDBOutput_5);
							}
						}
					}
				}

				/**
				 * [tDBOutput_5 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
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
		final JOB_cubes JOB_cubesClass = new JOB_cubes();

		int exitCode = JOB_cubesClass.runJobInTOS(args);

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
			java.io.InputStream inContext = JOB_cubes.class.getClassLoader()
					.getResourceAsStream("awc/job_cubes_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = JOB_cubes.class.getClassLoader()
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
			tDBInput_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBInput_1) {
			globalMap.put("tDBInput_1_SUBPROCESS_STATE", -1);

			e_tDBInput_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : JOB_cubes");
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
 * 190588 characters generated by Talend Open Studio for Data Integration on the
 * 16 février 2023 à 15:53:07 WEST
 ************************************************************************************************/