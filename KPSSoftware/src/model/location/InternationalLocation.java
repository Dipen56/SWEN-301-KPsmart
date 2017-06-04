package model.location;

/**
 * This class represents a location outside of New Zealand. An international location can be used as either the origin
 * or the destination of mails.
 *
 * @author Hector
 * @version 2017/5/20
 */
public class InternationalLocation extends Location {

    /**
     * The name of the city
     */
    private final String cityName;

    /**
     * Constructor
     *
     * @param locationId
     * @param cityName
     */
    public InternationalLocation(int locationId, String cityName) {
        super(locationId);
        this.cityName = cityName;
    }

    @Override
    public String getLocationName() {
        return cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof InternationalLocation))
            return false;

        InternationalLocation that = (InternationalLocation) o;

        return cityName.equals(that.cityName);
    }

    @Override
    public int hashCode() {
        return cityName.hashCode();
    }

    @Override
    public String toString() {
        return "InternationalLocation{" +
                "locationName='" + cityName + '\'' +
                '}';
    }
}
