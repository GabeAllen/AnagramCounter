import java.util.*;
import java.io.*;
public class Anagrams {
    public static void main(String[] args) {
        //read file into an array list
        ArrayList<String> listofWords = readTXTfileIntoList("anagram_words.txt");
        //organize array list into a map of anagrams
        Map<String,ArrayList<String>> anagramMap = mapAnagramsFromList(listofWords);
        //ask user for words and see anagrams of those words in the map
        cFunctionality(anagramMap);
        //(b)Randomly choose any word to show to the user, ask the user to input a second word.
        //Output whether the two words are anagrams. Repeat this until the user decides to stop
        bFunctionality(anagramMap,listofWords);
        //(A)Randomly choose any word to show to the user. Then count how many words the user
        //inputs that are anagrams of the randomly chosen word. When the user decides to stop
        //entering words, show how many total distinct words the user entered and how many of
        //them were anagrams
        //aFunctionality(anagramMap,listofWords);
    }
    static ArrayList<String> readTXTfileIntoList(String fileName) {
        ArrayList<String> listOfWords = new ArrayList<>();
        try{
            Scanner scan = new Scanner(new  File(fileName));
            while(scan.hasNext()){
                String currentWord = scan.nextLine();
                listOfWords.add(currentWord);
            }
        }catch(FileNotFoundException e){
            System.err.println("Could not find '"+ fileName+"'");
        }

        return listOfWords;
    }

    static Map<String,ArrayList<String>> mapAnagramsFromList(ArrayList<String> listOfWords){
        Map<String,ArrayList<String>> anagramMap = new HashMap<>();
        for (int i = 0; i < listOfWords.size(); i++) {
            //get the word from the list
            String originalWord = listOfWords.get(i);
            //alphabetize the word
            char tempArray[] = originalWord.toCharArray();
            Arrays.sort(tempArray);
            String sortedWord = new String(tempArray);
            //if the sorted word is in the map
            if(anagramMap.containsKey(sortedWord)){
                //add the original word to the sorted word's list
                anagramMap.get(sortedWord).add(originalWord);
            }
            //if the sorted word isn't in the map
            else{
                // put it in the map and give it a list with the original word
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(originalWord);
                anagramMap.put(sortedWord,newList);
            }
        }
        return anagramMap;
    }

    static void cFunctionality(Map<String,ArrayList<String>> anagramMap){
        Scanner scan = new Scanner(System.in);
        System.out.println("Input a word to see its anagrams. Type 's' to stop.");
        String userWord = scan.next().toLowerCase();
        while(!userWord.equals("s")&&!userWord.equals("S")){
            char tempArray[] = userWord.toCharArray();
            Arrays.sort(tempArray);
            String sortedUserWord = new String(tempArray);
            //if the users word has any anagrams in the map, print them and move on
            if(anagramMap.containsKey(sortedUserWord)){
                String outputString = "The anagrams of "+ userWord+" are:\n";
                for (int i = 0; i < anagramMap.get(sortedUserWord).size(); i++) {
                    if(!userWord.equals(anagramMap.get(sortedUserWord).get(i))){
                        outputString += anagramMap.get(sortedUserWord).get(i)+"\n";
                    }
                }
                System.out.println(outputString);
            }
            //if not say there are no anagrams in the map
            else{
                System.out.println("The map didn't contain any anagrams of that word. Type a new word.");
            }
            userWord = scan.next().toLowerCase();
        }
        System.out.println("Done");

    }

    static void bFunctionality(Map<String,ArrayList<String>> anagramMap, ArrayList<String> listOfWords){
        System.out.println("Here is a randomly chosen word. Type a word to see if it is an anagram. Type 's' to stop ");
        Scanner scan = new Scanner(System.in);
        String userInput = "";
        while(!userInput.equals("s")&&!userInput.equals("S")) {
            //grab a random word from the list of words
            Random generator = new Random();
            int max = listOfWords.size();
            String randomWord = listOfWords.get((int) ((Math.random() * (max - 0)) + 0));
            System.out.println(randomWord);
            //sort the random word to get ready to check it against the user word
            char tempArray[] = randomWord.toCharArray();
            Arrays.sort(tempArray);
            String sortedRandomWord = new String(tempArray);
            //Get the user input
            userInput = scan.next().toLowerCase();
            //sort the user input
            char tempArray2[] = userInput.toCharArray();
            Arrays.sort(tempArray2);
            String sortedUserInput = new String(tempArray2);
            //Check if the user inputted an anagram
            if(userInput.equals("s")||userInput.equals("S")){
                break;
            }
            else if (sortedUserInput.equals(sortedRandomWord)) {
                System.out.println("Yes, '" + userInput + "' is an anagram of " + randomWord);
            } else {
                System.out.println("No, '" + userInput + "' is not an anagram of " + randomWord);
            }
            System.out.println("Here's another word:");
        }
        System.out.println("done");
    }

//    static int aFunctionality(Map<String,ArrayList<String>> anagramMap, ArrayList<String> listOfWords){
//        int total = 0;
//        Scanner scan = new Scanner(System.in);
//        //get a random word
//        Random generator = new Random();
//        int max = listOfWords.size();
//        String randomWord = listOfWords.get((int) ((Math.random() * (max - 0)) + 0));
//        System.out.println("Here's a random word. Keep typing words you think are anagrams.\n" +
//                " When you're done, hit enter with nothing in the terminal.");
//        System.out.println(randomWord);
//        String userInput = scan.next().toLowerCase();
//        ArrayList<String> userWords = new ArrayList<>();
//        while(userInput.length()>0){
//            userWords.add(scan.next());
//        }
//        System.out.println(userWords);
//        System.out.println("You typed "+total+" anagrams of "+ randomWord);
//        return total;
//    }
}
