package grsu.reports;

/**
 * Created by beerk on 12.04.2017.
 */
public interface Reportable<T> {
    void generate(T addition);
}
