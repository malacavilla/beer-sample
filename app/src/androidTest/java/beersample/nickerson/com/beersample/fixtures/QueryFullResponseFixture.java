package beersample.nickerson.com.beersample.fixtures;

import java.util.ArrayList;

import beersample.nickerson.com.beersample.models.Beer;
import beersample.nickerson.com.beersample.models.Brewery;
import beersample.nickerson.com.beersample.models.CheckinItem;
import beersample.nickerson.com.beersample.models.Checkins;
import beersample.nickerson.com.beersample.models.QueryFullResponse;
import beersample.nickerson.com.beersample.models.QueryResponse;
import beersample.nickerson.com.beersample.models.UntappdLocation;

/**
 * Fixture for {@link QueryFullResponse}.
 */
public class QueryFullResponseFixture {

    public static QueryFullResponse getFullObject() {
        final QueryFullResponse result = new QueryFullResponse();
        result.response = new QueryResponse();

        result.response.checkins = new Checkins();
        result.response.checkins.count = 1;
        result.response.checkins.items = new ArrayList<>();
        result.response.checkins.items.add(getFullCheckinItem());

        return result;
    }

    private static CheckinItem getFullCheckinItem() {
        final CheckinItem result = new CheckinItem();
        result.brewery = getFullBreweryItem();
        result.beer = getFullBeerItem();

        return result;
    }

    private static Beer getFullBeerItem() {
        final Beer result = new Beer();
        result.active = 1;
        result.wishList = false;
        result.rating = 5;
        result.abv = 6.66;
        result.id = 1;
        result.label = "http://www.example.com/label";
        result.name = "Sample Beer";
        result.style = "Double IPA";

        return result;
    }

    private static Brewery getFullBreweryItem() {
        final Brewery result = new Brewery();
        result.location = getFullUntappdLocation();
        result.country = "USA";
        result.label = "label";
        result.slug = "slug";
        result.active = 1;
        result.id = 1;
        result.name = "Sample Brewery";

        return result;
    }

    private static UntappdLocation getFullUntappdLocation() {
        final UntappdLocation result = new UntappdLocation();
        result.longitude = 44.44;
        result.latitude = -44.44;
        result.city = "Boston";
        result.state = "MA";

        return result;
    }
}
