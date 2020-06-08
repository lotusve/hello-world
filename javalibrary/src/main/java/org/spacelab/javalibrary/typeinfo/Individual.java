package org.spacelab.javalibrary.typeinfo;

/**
 * page 497
 */
public class Individual implements Comparable<Individual> {

    private static long counter = 0;

    private final long id = counter++;

    private String name;

    public Individual() {
    }

    public Individual(String name) {
        this.name = name;
    }

    public long id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Individual && id == ((Individual) o).id;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (name != null) {
            result = 37 * result + name.hashCode();
        } else {
            result = 37 * result + (int) id;
        }
        return result;
    }

    @Override
    public int compareTo(Individual individual) {
        String first = getClass().getSimpleName();
        String individualFirst = individual.getClass().getSimpleName();
        int firstCompare = first.compareTo(individualFirst);
        if (firstCompare != 0) {
            return firstCompare;
        }

        if (name != null && individual.name != null) {
            int secondCompare = name.compareTo(individual.name);
            if (secondCompare != 0) {
                return secondCompare;
            }
        }

        // (individual.id < id ? -1 : (individual.id == id ? 0 : 1));
        return (Long.compare(individual.id, id));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + (name == null ? "" : name);
    }

}
