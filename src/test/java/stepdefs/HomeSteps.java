package stepdefs;

import io.cucumber.java.en.*;
import pages.HomePage;

public class HomeSteps {
    HomePage homePage = new HomePage();

    @Then("verify user is successfully logged in to the account")
    public void verify_user_is_successfully_logged_in_to_the_account() {
        homePage.verifyPage();
    }

    @When ("user clicks on transfer between accounts")
    public void user_clicks_on_transfer_between_accounts() {
        homePage.clickOnTransferBetweenAccounts();

            }




}
