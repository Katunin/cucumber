package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.QuoteForm;
import pages.QuoteResult;

import static org.assertj.core.api.Assertions.assertThat;

public class MarketStepDefs {
    @Given("I navigate to {string} page")
    public void iNavigateToPage(String page) {
        switch (page) {
            case "quote":
                new QuoteForm().open();
                break;
            default:
                throw new RuntimeException("Unknown page: " + page);
        }
    }

    @When("I fill out {string} fields")
    public void iFillOutFields(String scope) {
        QuoteForm form = new QuoteForm();
        form.fillUsername("skryabin");
        form.fillEmail("slava@skryabin.com");
        form.fillPassword("welcome");
        form.fillConfirmPassword("welcome");
        form.fillName("Slava", "Skryabin");
        form.acceptPrivacy();
    }



    @Then("I verify {string} fields")
    public void iVerifyFields(String scope) {
        QuoteResult result = new QuoteResult();
        String resultText = result.getResult();

        assertThat(resultText).contains("skryabin");
        assertThat(resultText).contains("slava@skryabin.com");
        assertThat(resultText).contains("Slava Skryabin");
        assertThat(resultText).doesNotContain("welcome");
        assertThat(result.getPrivacy()).isEqualTo("true");
        assertThat(result.getPassword()).isEqualTo("[entered]");
        assertThat(result.areAllResultElementsBold()).isTrue();
    }

    @When("I fill out {string} field with {string}")
    public void iFillOutFieldWith(String field, String value) {
        QuoteForm form = new QuoteForm();
        form.fillField(field, value);
    }

    @Then("I verify error {string} displayed")
    public void iVerifyErrorDisplayed(String error) {
        assertThat(new QuoteForm().getBody()).containsIgnoringCase(error);
    }

    @And("I submit the form")
    public void iSubmitTheForm() {
        new QuoteForm().submitForm();
    }

    @When("I click on {string} button")
    public void iClickOnButton(String buttonText) {
        QuoteForm form = new QuoteForm();
        form.clickButton(buttonText);
    }

    @And("I accept alert")
    public void iAcceptAlert() {
        new QuoteForm().acceptAlert();
    }

    @Then("I verify alert text as {string}")
    public void iVerifyAlertTextAs(String text) {
        assertThat(new QuoteForm().getAlertText()).containsIgnoringCase(text);
    }

    @And ("I fill out optional fields")
    public void iFillOutOptionalFields() throws Throwable{
        QuoteForm form =new QuoteForm();
        form.selectCountry("Canada");

    }



    @When("I type {string}")
    public void iType(String email) {
        QuoteForm form = new QuoteForm();
        form.fillEmail(email);
    }
}
