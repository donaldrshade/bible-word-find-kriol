import java.util.ArrayList;
import java.util.Arrays;

public class Puzzle {
	private char[][] display;
	private Word[] words;
	
	
	public Puzzle (int pNum) {
		//get 
		int s = getSize(pNum);
		int wCt = getWordCt(pNum);
		
		display = new char[s][s];
		words = new Word[wCt];
		
		String[] pool = getWords(pNum);
		
		int[] wordSizes = getWordSizes(pNum);
		if(wordSizes.length == 1) {
			wordSizes = new int[] {wordSizes[0], wordSizes[0]};
		}
		ArrayList<String> options = new ArrayList<String>();
		for(int i = 0; i < pool.length; i++) {
			if(pool[i].length() >= wordSizes[0] && pool[i].length() <= wordSizes[1]) {
				options.add(pool[i]);
			}
		}
		
		for(int i = 0; i < words.length; i++) {
			int rand = (int) (Math.random()*options.size());
			words[i] = new Word(options.remove(rand));
		}
		
		
		fillDisplay(false);
		
		Arrays.sort(words);
	}

	
	

	private int getSize(int pNum) {
		
		return Tester.getSize();
	}
	
	private int getWordCt(int pNum) {
		
		return Tester.getWordCt();
	}
	
	private String[] getWords(int pNum) {
		return Tester.getWordList();
		
	}
	
	private int[] getWordSizes(int pNum) {
		return Tester.getWSizes();
		
	}
	
	private void fillDisplay(boolean overlap) {
		for(int r = 0; r < words.length; r++) {
			String s = words[r].getWord();
			for(int c = 0; c < s.length(); c++) {
				display[r][c] = Character.toUpperCase(s.charAt(c));
			}
			for(int c = s.length(); c < display.length; c++) {
				display[r][c] = randLetter();
			}
			words[r].setStartPt(r, 0);
			words[r].setEndPt(r,s.length());
		}
		for (int r = words.length; r < display.length; r++) {
			for(int c = 0; c < display.length; c++) {
				display[r][c] = randLetter();
			}
		}

		
		
		
	}
	
	private char randLetter() {
		return (char) (int) (Math.random() * ('Z'-'A') + 'A');
	}
	
	public char[][] getGrid() {
		return display.clone();
	}
	
	public Word[] getWords() {
		return words.clone();
	}
}
class Word implements Comparable<Word>{
	private String word;
	private int[] startPt = {0,0};
	private int[] endPt = {0,0};
	
	public Word(String w) {
		word = w;
	}
	
	public int compareTo (Word other){
		return this.word.compareToIgnoreCase(other.word);
	}
	
	public String getWord() {
		return word;
	}
	
	public int[] getStartPt(){
		return startPt.clone();
	}
	
	public void setStartPt(int a, int b) {
		if(a < 0 || b < 0) return;
		startPt[0] = a;
		startPt[1] = b;
	}
	
	public int[] getEndPt(){
		return endPt.clone();
	}
	
	public void setEndPt(int a, int b) {
		if(a < 0 || b < 0) return;
		endPt[0] = a;
		endPt[1] = b;
	}
}
