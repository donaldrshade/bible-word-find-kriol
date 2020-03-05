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
		
		
		fillDisplay(true);
		
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
	
	private void fillDisplay(boolean overlap) {
		if(!overlap) {
			fillDisplayBasic();
			return;
		}

		
		//pre - words list is sorted by length
		
		Stack<char[][]> tempGrids = new Stack<char[][]>();
		//- this is for storing the grids as recursion goes on
		int[][] rc = new int[words.length][2];
		//- this is for storing the positions of each word so that when we restart, we don't
		//hit the same position over and over again
		for(int[] arr : rc) {
			for(int i = 0; i < arr.length; i++) {
				arr[i] = -1;
			}
		}
		

		ArrayList<boolean[][]> checkedSpots = new ArrayList<boolean[][]>();
		//this will be empty if this word's position has not been tried anywhere yet
		
		
		int RESET_CT = 0;
		//- this is for breaking the loop if recursion goes too long
		
		
		
		for(int i = 0; i < words.length; i++) { 
			if(i < 0) {
				fillDisplayBasic();
				return;
			}
			//- this loop indicates which word in the list we are trying to stick in the grid. We
			//-	will backtrack in this loop occasionally with an i -= 2 (followed by the i++ is i--)
			
			
			tempGrids.add(display.clone());
			//save a copy of the board before we change it
			
			char[] word = words[i].getWord().toCharArray();
			//longest word is at front
						
			
			
			int s_r;
			int s_c;
			
			if(checkedSpots.size() == i) {
				//nothing has been tried for this word and setup yet
				s_r = (int) (Math.random() * display.length);
				s_c = (int) (Math.random() * display.length);
				checkedSpots.add(new boolean[display.length][display.length]);
				
			} else {
				s_r = rc[i][0] +1 ;
				s_c = rc[i][1];
				
				if(s_r==display.length) { //correction for incremented loaded values 
					s_c %= display.length;
					s_r = (s_r + 1)%display.length;
					
				}
			}
			//loading will skip other dirs on a space
			
			
			int r = s_r; 
			int c = s_c;
			int d;
			
			boolean worked = false;
			
			while(!checkedSpots.get(i)[r][c]) {
				checkedSpots.get(i)[r][c] = true;
				
				//test display[r][c] in all 8 dirs as possible location
				
				int dir = (int) (Math.random() * 8);
				
				for(d = dir ; d < dir+8; d++) {
					worked = fitWord(r,c,d, word);
					
					if(worked) {
						writeWord(r,c,d,word);
						words[i].setStartPt(r, c);
						switch(d%8) {
						case 0: words[i].setEndPt(r-(word.length-1),c); break; //N
						case 1: words[i].setEndPt(r-(word.length-1),c+(word.length-1)); break; //NE
						case 2: words[i].setEndPt(r,c+(word.length-1)); break; //E
						case 3: words[i].setEndPt(r+(word.length-1),c+(word.length-1)); break; //SE
						case 4: words[i].setEndPt(r+(word.length-1),c); break; //S
						case 5: words[i].setEndPt(r+(word.length-1),c-(word.length-1)); break; //SW
						case 6: words[i].setEndPt(r,c-(word.length-1)); break; //W
						case 7: words[i].setEndPt(r-(word.length-1),c-(word.length-1)); break; //NW
						}
						break;
					}
				}
				if(worked) {
					//board should already be filled at r,c,d; and word's info filled
					
					rc[i][0] = r;
					rc[i][1] = c;
					break;
				}
				
				c = (c+1)%display.length;
				r += (c == 0)? 1 : 0;
				r %= display.length;
			}
			
			if(!worked) {
				RESET_CT++;
				rc[i][0] = -1;
				rc[i][1] = -1;//erase any rc for this word, for last word is saved as restart pt
				i-= 2; //will be incremented due to i++ of for loop
				 //erases the change before this "change"
				display = tempGrids.pop();
				if(!tempGrids.isEmpty()) {
					display = tempGrids.pop(); //sets display to the right version (will get pushed back on)
					
				}
				checkedSpots.remove(checkedSpots.size()-1); //all true because !worked
			}
		
		}
		//board got generated
		for(char[] arr : display) {
			for(int i = 0; i < arr.length; i++) {
				if(arr[i] == 0) {
					arr[i] = randLetter();
				}
			}
		}
		
	}
	
	private boolean fitWord(int r, int c, int d, char[] word) {
		return placeWord(r, c, d, word, false);
	}
	
	private void writeWord(int r, int c, int d, char[] word) {
		placeWord(r,c,d,word,true);
	}
	
	private boolean placeWord(int r, int c, int d, char[] word, boolean write) {
		for(int delta = 0; delta < word.length; delta++) {
			switch(d%8) {
			case 0: //N
				if(!fits(r-delta, c, word[delta], write)) {
					return false;
				}
				break;
			case 1: //NE
				if(!fits(r-delta, c+delta, word[delta], write)) {
					return false;
				}
				break;
			case 2://E
				if(!fits(r, c+delta, word[delta], write)) {
					return false;
				}
				break;
			case 3://SE
				if(!fits(r+delta, c +delta, word[delta], write)) {
					return false;
				}
				break;
			case 4://S
				if(!fits(r+delta, c, word[delta], write)) {
					return false;
				}
				break;
			case 5://SW
				if(!fits(r+delta, c-delta, word[delta], write)) {
					return false;
				}
				break;
			case 6://W
				if(!fits(r, c-delta, word[delta], write)) {
					return false;
				}
				break;
			case 7://NW
				if(!fits(r-delta, c-delta, word[delta], write)) {
				return false;
				}
				break;
			}
			
		}
		return true;
	}
	
	private boolean fits(int r, int c, char ch, boolean write) {
		if(r < 0 || c < 0 || r >= display.length || c >= display.length) {
			return false;
		}
		if(display[r][c] == 0 || display[r][c] == ch) {
			if(write) {
				display[r][c] = Character.toUpperCase(ch);
			}
			return true;
		}
		return false;
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
