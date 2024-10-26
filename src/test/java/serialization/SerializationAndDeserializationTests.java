package serialization;

import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.pet.Category;
import pojo.pet.Pet;
import pojo.pet.Tag;
import pojo.user.User;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class SerializationAndDeserializationTests {

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(123);
        pet.setCategory(category);
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        Pet actualPet = given().log().all().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200).extract().as(Pet.class);

        Assert.assertEquals(actualPet.getId(), pet.getId());
        Assert.assertEquals(actualPet.getCategory().getId(), pet.getCategory().getId());
        Assert.assertEquals(actualPet.getCategory().getName(), pet.getCategory().getName());
        Assert.assertEquals(actualPet.getPhotoUrls().get(0), pet.getPhotoUrls().get(0));
        Assert.assertEquals(actualPet.getTags().get(0).getId(), pet.getTags().get(0).getId());
        Assert.assertEquals(actualPet.getTags().get(0).getName(), pet.getTags().get(0).getName());
        Assert.assertEquals(actualPet.getStatus(), pet.getStatus());
    }
}
