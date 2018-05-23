package ueb08;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TweetSammlungImpl implements TweetSammlung {

    Map<String, Integer> woerter = new TreeMap<>();

    @Override
    public void setStopwords(File file) throws FileNotFoundException {

    }

    @Override
    public void ingest(String tweet) {
        List<String> stringList = TweetSammlung.tokenize(tweet);

        int alt = 1;
        int neu = 0;

        for (int i = 0; i < stringList.size(); i++) {
            if (!(woerter.containsKey(stringList.get(i)))) {
                woerter.put(stringList.get(i), alt);
            } else {
                alt = woerter.get(stringList.get(i));
                neu = alt + 1;
                woerter.replace(stringList.get(i), neu);
            }
        }
    }

    @Override
    public Iterator<String> vocabIterator() {
        Set<String> voc = woerter.keySet();
        return voc.iterator();
    }

    @Override
    public Iterator<String> topHashTags() {
        List<Pair<String, Integer>> voc = new LinkedList<>();
        for (String word : woerter.keySet()) {
            if (word.startsWith("#")) {
                voc.add(Pair.of(word, woerter.get(word)));
            }
        }
        voc.sort();
    return null;
    }


    @Override
    public Iterator<String> topWords() {
        return null;
    }

    @Override
    public Iterator<Pair<String, Integer>> topTweets() {
        return null;
    }
}
