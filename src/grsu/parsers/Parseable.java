package grsu.parsers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface Parseable<T> {
    List<T> parse(String var1) throws IOException, ParseException;
}
