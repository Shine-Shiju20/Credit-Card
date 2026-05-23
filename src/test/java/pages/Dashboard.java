package pages;

import org.openqa.selenium.By;

public class Dashboard {

    public By welcomeText =
            By.xpath("//div[@class='page-header']//h1");
    public By financialOverviewText =
            By.xpath("//div[@class='page-header']//p");

    public By totalBalanceCard =
            By.xpath("//p[text()='Total Balance']/ancestor::div[contains(@class,'stat-card')]");

    public By totalBalanceAmount =
            By.xpath("//p[text()='Total Balance']/following-sibling::h3");

    public By totalAccountsText =
            By.xpath("//span[contains(text(),'Across')]");



    public By availableBalanceCard =
            By.xpath("//p[text()='Available Balance']/ancestor::div[contains(@class,'stat-card')]");

    public By availableBalanceAmount =
            By.xpath("//p[text()='Available Balance']/following-sibling::h3");



    public By kycStatusCard =
            By.xpath("//p[text()='KYC Status']/ancestor::div[contains(@class,'stat-card')]");

    public By kycStatusValue =
            By.xpath("//p[text()='KYC Status']/following-sibling::h3");

    public By kycBadge =
            By.xpath("//p[text()='KYC Status']/following-sibling::span");



    public By accountStatusCard =
            By.xpath("//p[text()='Account Status']/ancestor::div[contains(@class,'stat-card')]");

    public By accountStatusValue =
            By.xpath("//p[text()='Account Status']/following-sibling::h3");

    public By accountStatusBadge =
            By.xpath("//p[text()='Account Status']/following-sibling::span");




    public By accountsSectionTitle =
            By.xpath("//h2[contains(.,'Your Accounts')]");

    public By allAccountCards =
            By.xpath("//div[contains(@class,'account-card-dash')]");




    public By savingsAccountCard =
            By.xpath("//span[text()='Savings']/ancestor::div[contains(@class,'account-card-dash')]");

    public By savingsAccountNumber =
            By.xpath("//span[text()='Savings']/ancestor::div[contains(@class,'account-card-dash')]//span[@class='acc-value']");

    public By savingsBalance =
            By.xpath("//span[text()='Savings']/ancestor::div[contains(@class,'account-card-dash')]//span[contains(@class,'acc-amount')]");

    public By savingsIFSC =
            By.xpath("//span[text()='Savings']/ancestor::div[contains(@class,'account-card-dash')]//span[contains(@class,'acc-ifsc')]");




    public By salaryAccountCard =
            By.xpath("//span[text()='Salary']/ancestor::div[contains(@class,'account-card-dash')]");

    public By salaryAccountNumber =
            By.xpath("//span[text()='Salary']/ancestor::div[contains(@class,'account-card-dash')]//span[@class='acc-value']");

    public By salaryBalance =
            By.xpath("//span[text()='Salary']/ancestor::div[contains(@class,'account-card-dash')]//span[contains(@class,'acc-amount')]");

    public By salaryIFSC =
            By.xpath("//span[text()='Salary']/ancestor::div[contains(@class,'account-card-dash')]//span[contains(@class,'acc-ifsc')]");




    public By emptyStateTitle =
            By.xpath("//h3[text()='No Active Accounts']");

    public By emptyStateMessage =
            By.xpath("//p[text()='Your accounts will appear here']");




    public By accountHolderSection =
            By.xpath("//h2[contains(.,'Account Holder')]");



    public By fullNameLabel =
            By.xpath("//span[text()='Full Name']");

    public By fullNameValue =
            By.xpath("//span[text()='Full Name']/following-sibling::span");



    public By emailValue =
            By.xpath("//span[text()='Email']/following-sibling::span");



    public By phoneValue =
            By.xpath("//span[text()='Phone']/following-sibling::span");



    public By occupationValue =
            By.xpath("//span[text()='Occupation']/following-sibling::span");



    public By roleValue =
            By.xpath("//span[text()='Role']/following-sibling::span");



    public By memberSinceValue =
            By.xpath("//span[text()='Member Since']/following-sibling::span");

}