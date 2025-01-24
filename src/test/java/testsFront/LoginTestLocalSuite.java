package testsFront;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;

public class LoginTestLocalSuite extends BaseLocalSuite {

    @Test
    public void testEmailBlankError() {
        loginPage.login("", "secret_sauce");
        boolean isEmailBlankErrorDisplayed = loginPage.isEmailBlankErrorDisplayed();
        Assert.assertTrue(isEmailBlankErrorDisplayed, "Epic sadface: Username is required");
    }

    @Test
    public void testPasswordBlankError() {
        loginPage.login("standard_user", "");
        boolean isPasswordBlankErrorDisplayed = loginPage.isPasswordBlankErrorDisplayed();
        Assert.assertTrue(isPasswordBlankErrorDisplayed, "Epic sadface: Password is required");
    }

    @Test
    public void testInvalidCredentialsError() {
        loginPage.login("emailinvalido", "senhaerrada");
        boolean isInvalidCredentialsErrorDisplayed = loginPage.isInvalidCredentialsErrorDisplayed();
        Assert.assertTrue(isInvalidCredentialsErrorDisplayed, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testValidLogin() {
        loginPage.login("standard_user", "secret_sauce");
        HomePage homePage = new HomePage(driver);

        String expectedMessage = "Products";
        String actualMessage = homePage.getAccessLevelMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "O texto da mensagem de nível de acesso está incorreto.");
    }
}
