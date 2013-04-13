package pojo;

import java.util.Date;

public class Words implements java.io.Serializable {

	private long vocId;
	private Long wid;
	private String word;
	private char alphabet;
	private char antiAlphabet;
	private String antiWord;
	private String alias1;
	private String alias2;
	private Integer wordLevel;
	private int orderId;
	private String meaningEn;
	private String meaningCh;
	private Long designerId;
	private String type;
	private Long matchingId;
	private Date lastModified;
	private int status;
	private boolean band3;
	private boolean band4;
	private boolean band6;
	private boolean bandGraduate;
	private byte bandPets1;
	private boolean bandPets2;
	private boolean bandPets3;
	private boolean bandPets4;
	private boolean bandPets5;
	private boolean importantBand3;
	private boolean importantBand4;
	private boolean importantBand6;
	private boolean importantBandGraduate;
	private boolean importantPets1;
	private boolean importantPets2;
	private boolean importantPets3;
	private boolean importantPets4;
	private boolean importantPets5;
	private byte catId;
	private String example;

	public Words() {
	}

	public Words(long vocId, String word, char alphabet,
			char antiAlphabet, String antiWord, int orderId, Date lastModified,
			int status, boolean band3, boolean band4, boolean band6,
			boolean bandGraduate, byte bandPets1, boolean bandPets2,
			boolean bandPets3, boolean bandPets4, boolean bandPets5,
			boolean importantBand3, boolean importantBand4,
			boolean importantBand6, boolean importantBandGraduate,
			boolean importantPets1, boolean importantPets2,
			boolean importantPets3, boolean importantPets4,
			boolean importantPets5, byte catId, String example) {
		this.vocId = vocId;
		this.word = word;
		this.alphabet = alphabet;
		this.antiAlphabet = antiAlphabet;
		this.antiWord = antiWord;
		this.orderId = orderId;
		this.lastModified = lastModified;
		this.status = status;
		this.band3 = band3;
		this.band4 = band4;
		this.band6 = band6;
		this.bandGraduate = bandGraduate;
		this.bandPets1 = bandPets1;
		this.bandPets2 = bandPets2;
		this.bandPets3 = bandPets3;
		this.bandPets4 = bandPets4;
		this.bandPets5 = bandPets5;
		this.importantBand3 = importantBand3;
		this.importantBand4 = importantBand4;
		this.importantBand6 = importantBand6;
		this.importantBandGraduate = importantBandGraduate;
		this.importantPets1 = importantPets1;
		this.importantPets2 = importantPets2;
		this.importantPets3 = importantPets3;
		this.importantPets4 = importantPets4;
		this.importantPets5 = importantPets5;
		this.catId = catId;
		this.example = example;
	}

	public Words(long vocId, Long wid, String word, char alphabet,
			char antiAlphabet, String antiWord, String alias1, String alias2,
			Integer wordLevel, int orderId, String meaningEn, String meaningCh,
			Long designerId, String type, Long matchingId, Date lastModified,
			int status, boolean band3, boolean band4, boolean band6,
			boolean bandGraduate, byte bandPets1, boolean bandPets2,
			boolean bandPets3, boolean bandPets4, boolean bandPets5,
			boolean importantBand3, boolean importantBand4,
			boolean importantBand6, boolean importantBandGraduate,
			boolean importantPets1, boolean importantPets2,
			boolean importantPets3, boolean importantPets4,
			boolean importantPets5, byte catId, String example) {
		this.vocId = vocId;
		this.wid = wid;
		this.word = word;
		this.alphabet = alphabet;
		this.antiAlphabet = antiAlphabet;
		this.antiWord = antiWord;
		this.alias1 = alias1;
		this.alias2 = alias2;
		this.wordLevel = wordLevel;
		this.orderId = orderId;
		this.meaningEn = meaningEn;
		this.meaningCh = meaningCh;
		this.designerId = designerId;
		this.type = type;
		this.matchingId = matchingId;
		this.lastModified = lastModified;
		this.status = status;
		this.band3 = band3;
		this.band4 = band4;
		this.band6 = band6;
		this.bandGraduate = bandGraduate;
		this.bandPets1 = bandPets1;
		this.bandPets2 = bandPets2;
		this.bandPets3 = bandPets3;
		this.bandPets4 = bandPets4;
		this.bandPets5 = bandPets5;
		this.importantBand3 = importantBand3;
		this.importantBand4 = importantBand4;
		this.importantBand6 = importantBand6;
		this.importantBandGraduate = importantBandGraduate;
		this.importantPets1 = importantPets1;
		this.importantPets2 = importantPets2;
		this.importantPets3 = importantPets3;
		this.importantPets4 = importantPets4;
		this.importantPets5 = importantPets5;
		this.catId = catId;
		this.example = example;
	}

	public long getVocId() {
		return this.vocId;
	}

	public void setVocId(long vocId) {
		this.vocId = vocId;
	}

	public Long getWid() {
		return this.wid;
	}

	public void setWid(Long wid) {
		this.wid = wid;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public char getAlphabet() {
		return this.alphabet;
	}

	public void setAlphabet(char alphabet) {
		this.alphabet = alphabet;
	}

	public char getAntiAlphabet() {
		return this.antiAlphabet;
	}

	public void setAntiAlphabet(char antiAlphabet) {
		this.antiAlphabet = antiAlphabet;
	}

	public String getAntiWord() {
		return this.antiWord;
	}

	public void setAntiWord(String antiWord) {
		this.antiWord = antiWord;
	}

	public String getAlias1() {
		return this.alias1;
	}

	public void setAlias1(String alias1) {
		this.alias1 = alias1;
	}

	public String getAlias2() {
		return this.alias2;
	}

	public void setAlias2(String alias2) {
		this.alias2 = alias2;
	}

	public Integer getWordLevel() {
		return this.wordLevel;
	}

	public void setWordLevel(Integer wordLevel) {
		this.wordLevel = wordLevel;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getMeaningEn() {
		return this.meaningEn;
	}

	public void setMeaningEn(String meaningEn) {
		this.meaningEn = meaningEn;
	}

	public String getMeaningCh() {
		return this.meaningCh;
	}

	public void setMeaningCh(String meaningCh) {
		this.meaningCh = meaningCh;
	}

	public Long getDesignerId() {
		return this.designerId;
	}

	public void setDesignerId(Long designerId) {
		this.designerId = designerId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getMatchingId() {
		return this.matchingId;
	}

	public void setMatchingId(Long matchingId) {
		this.matchingId = matchingId;
	}

	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isBand3() {
		return this.band3;
	}

	public void setBand3(boolean band3) {
		this.band3 = band3;
	}

	public boolean isBand4() {
		return this.band4;
	}

	public void setBand4(boolean band4) {
		this.band4 = band4;
	}

	public boolean isBand6() {
		return this.band6;
	}

	public void setBand6(boolean band6) {
		this.band6 = band6;
	}

	public boolean isBandGraduate() {
		return this.bandGraduate;
	}

	public void setBandGraduate(boolean bandGraduate) {
		this.bandGraduate = bandGraduate;
	}

	public byte getBandPets1() {
		return this.bandPets1;
	}

	public void setBandPets1(byte bandPets1) {
		this.bandPets1 = bandPets1;
	}

	public boolean isBandPets2() {
		return this.bandPets2;
	}

	public void setBandPets2(boolean bandPets2) {
		this.bandPets2 = bandPets2;
	}

	public boolean isBandPets3() {
		return this.bandPets3;
	}

	public void setBandPets3(boolean bandPets3) {
		this.bandPets3 = bandPets3;
	}

	public boolean isBandPets4() {
		return this.bandPets4;
	}

	public void setBandPets4(boolean bandPets4) {
		this.bandPets4 = bandPets4;
	}

	public boolean isBandPets5() {
		return this.bandPets5;
	}

	public void setBandPets5(boolean bandPets5) {
		this.bandPets5 = bandPets5;
	}

	public boolean isImportantBand3() {
		return this.importantBand3;
	}

	public void setImportantBand3(boolean importantBand3) {
		this.importantBand3 = importantBand3;
	}

	public boolean isImportantBand4() {
		return this.importantBand4;
	}

	public void setImportantBand4(boolean importantBand4) {
		this.importantBand4 = importantBand4;
	}

	public boolean isImportantBand6() {
		return this.importantBand6;
	}

	public void setImportantBand6(boolean importantBand6) {
		this.importantBand6 = importantBand6;
	}

	public boolean isImportantBandGraduate() {
		return this.importantBandGraduate;
	}

	public void setImportantBandGraduate(boolean importantBandGraduate) {
		this.importantBandGraduate = importantBandGraduate;
	}

	public boolean isImportantPets1() {
		return this.importantPets1;
	}

	public void setImportantPets1(boolean importantPets1) {
		this.importantPets1 = importantPets1;
	}

	public boolean isImportantPets2() {
		return this.importantPets2;
	}

	public void setImportantPets2(boolean importantPets2) {
		this.importantPets2 = importantPets2;
	}

	public boolean isImportantPets3() {
		return this.importantPets3;
	}

	public void setImportantPets3(boolean importantPets3) {
		this.importantPets3 = importantPets3;
	}

	public boolean isImportantPets4() {
		return this.importantPets4;
	}

	public void setImportantPets4(boolean importantPets4) {
		this.importantPets4 = importantPets4;
	}

	public boolean isImportantPets5() {
		return this.importantPets5;
	}

	public void setImportantPets5(boolean importantPets5) {
		this.importantPets5 = importantPets5;
	}

	public byte getCatId() {
		return this.catId;
	}

	public void setCatId(byte catId) {
		this.catId = catId;
	}

	public String getExample() {
		return this.example;
	}

	public void setExample(String example) {
		this.example = example;
	}

}
