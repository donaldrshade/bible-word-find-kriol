import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class PuzzleEngine {
	private char[][] display;
	private Word[] words;
	
	
	public PuzzleEngine (int pNum) {
		//get 
		int s = getSize();
		int wCt = getWordCt();
		
		display = new char[s][s];
		words = new Word[wCt];
		
		String[] pool = getWordList();
		
		int[] wordSizes = getWordSizes();
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
			Word insert = new Word(options.remove(rand));
			int x = 0;
			for(x = 0; x < i; x++) {
				if(words[x].getWord().length() < insert.getWord().length()) {
					Word sv = words[x];
					words[x] = insert;
					insert = sv;
				}
			}
			words[i] = insert; //fills words up by decreasing length
		}
		
		
		fillDisplayBasic();
		
		Arrays.sort(words);
	}


	private int getSize() {
		
		return Tester.getSize();
	}
	
	private int getWordCt() {
		
		return Tester.getWordCt();
	}
	
	private String[] getWordList() {
		return Tester.getWordList();
		
	}
	
	private int[] getWordSizes() {
		return Tester.getWSizes();
		
	}
	
	
	private void fillDisplayBasic() {
		int numWordsLeft = words.length;
		int numLinesLeft = display.length;
		
		//shuffle words
		for(int i = 0; i < words.length; i++) {
			int swap = (int)(Math.random() * (words.length - i));
			Word sv = words[i];
			words[i] = words[swap];
			words[swap] = sv;
		}


		for(int r = 0; r < display.length; r++) {
			if(numWordsLeft != numLinesLeft && (numWordsLeft == 0 || Math.random() >  numWordsLeft / (double) numLinesLeft)){
				//skip this line
				//System.err.println("skip line");
				for(int c = 0; c < display.length; c++) {
					display[r][c] = randLetter();
				}
				numLinesLeft --;
			} else {
				
				String s = words[words.length - numWordsLeft].getWord();
				
				boolean forwards = (Math.random() < 0.5);
				//System.out.println(s + " " + forwards + " r: " + r);
				int charSkip = (int) (Math.random() * (display.length - s.length()));
				int c = 0;
				for(c = 0; c < charSkip; c++) {
					display[r][(forwards)?c:display.length-1-c] = randLetter();
				}
			
				for(; c < charSkip + s.length(); c++) {
					display[r][(forwards)?c:display.length-1-c] = Character.toUpperCase(s.charAt(c-charSkip));
				}
				for(; c < display.length; c++) {
					display[r][(forwards)?c:display.length-1-c] = randLetter();
				}
			
				if(forwards) {
					words[words.length - numWordsLeft].setStartPt(r, charSkip);
					words[words.length - numWordsLeft].setEndPt(r,charSkip+ s.length()-1);
				} else {
					words[words.length - numWordsLeft].setStartPt(r, display.length - 1- charSkip);
					words[words.length - numWordsLeft].setEndPt(r,display.length-charSkip-s.length());
				}
				
				numLinesLeft--;
				numWordsLeft--;
				
				
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
