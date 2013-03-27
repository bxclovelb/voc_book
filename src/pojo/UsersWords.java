package pojo;

import java.util.Date;

public class UsersWords implements java.io.Serializable {

	private UsersWordsId id;
	private long vocId;
	private byte degree;
	private String notes;
	private boolean isAdditional;
	private Date createDate;
	private char alphabet;
	private char antiAlphabet;
	private byte catId;
	private Date exerciseDate;

	public UsersWords() {
	}

	public UsersWords(UsersWordsId id, long vocId, byte degree,
			boolean isAdditional, Date createDate, char alphabet,
			char antiAlphabet, byte catId) {
		this.id = id;
		this.vocId = vocId;
		this.degree = degree;
		this.isAdditional = isAdditional;
		this.createDate = createDate;
		this.alphabet = alphabet;
		this.antiAlphabet = antiAlphabet;
		this.catId = catId;
	}

	public UsersWords(UsersWordsId id, long vocId, byte degree,
			String notes, boolean isAdditional, Date createDate, char alphabet,
			char antiAlphabet, byte catId, Date exerciseDate) {
		this.id = id;
		this.vocId = vocId;
		this.degree = degree;
		this.notes = notes;
		this.isAdditional = isAdditional;
		this.createDate = createDate;
		this.alphabet = alphabet;
		this.antiAlphabet = antiAlphabet;
		this.catId = catId;
		this.exerciseDate = exerciseDate;
	}

	public UsersWordsId getId() {
		return this.id;
	}

	public void setId(UsersWordsId id) {
		this.id = id;
	}

	public long getVocId() {
		return this.vocId;
	}

	public void setVocId(long vocId) {
		this.vocId = vocId;
	}

	public byte getDegree() {
		return this.degree;
	}

	public void setDegree(byte degree) {
		this.degree = degree;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isIsAdditional() {
		return this.isAdditional;
	}

	public void setIsAdditional(boolean isAdditional) {
		this.isAdditional = isAdditional;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public byte getCatId() {
		return this.catId;
	}

	public void setCatId(byte catId) {
		this.catId = catId;
	}

	public Date getExerciseDate() {
		return this.exerciseDate;
	}

	public void setExerciseDate(Date exerciseDate) {
		this.exerciseDate = exerciseDate;
	}

}
