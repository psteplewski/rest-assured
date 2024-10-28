package utils;

import pojo.pet.Category;
import pojo.pet.Pet;
import pojo.pet.Tag;
import utils.enums.PetStatus;
import utils.enums.PetTag;
import utils.enums.PetsCategory;

import java.util.Collections;
import java.util.Random;

public class PetTestDataGenerator extends TestDataGenerator {

    public Pet generatePet() {
        PetsCategory petsCategory = randomPetCategory();
        PetTag petTag = randomPetTag();

        Tag tag = new Tag();
        tag.setId(petsCategory.getId());
        tag.setName(petTag.getTagyName());

        Category category = new Category();
        category.setId(petsCategory.getId());
        category.setName(petsCategory.getCategoryName());

        Pet pet = new Pet();
        pet.setName(faker().gameOfThrones().character());
        pet.setId(faker().number().numberBetween(1, 9999));
        pet.setCategory(category);
        pet.setTags(Collections.singletonList(tag));
        pet.setPhotoUrls(Collections.singletonList(faker().internet().url()));

        PetStatus petStatus = randomPetStatus();
        pet.setStatus(petStatus.getStatus());
        return pet;
    }

    private PetTag randomPetTag() {
        int pick = new Random().nextInt(PetTag.values().length);
        return PetTag.values()[pick];
    }

    private PetsCategory randomPetCategory() {
        int pick = new Random().nextInt(PetStatus.values().length);
        return PetsCategory.values()[pick];
    }

    private PetStatus randomPetStatus() {
        int pick = new Random().nextInt(PetStatus.values().length);
        return PetStatus.values()[pick];
    }


}
