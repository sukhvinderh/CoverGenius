package covergenius.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import covergenius.pages.PolicyInfoAndPayment;
import covergenius.pages.RentalCoverLandingPage;

public class RCVehicleRentalInsuraceQuotesTest extends BaseTest {

	private RentalCoverLandingPage rcLandingPage;
	private PolicyInfoAndPayment policyInfoPage;

	@Test(enabled = true)
	public void verifyPolicyQuoteForUnitedStates4x4VehicleFor1Day() {

		SoftAssert asserts = new SoftAssert();

		rcLandingPage = new RentalCoverLandingPage(driver);
		policyInfoPage = rcLandingPage.getPolicyQuoteForVehicleRental1Day("United States","4x4");

		asserts.assertEquals(policyInfoPage.getDestinationCountry(), "United States", "Destination Country");
		asserts.assertEquals(policyInfoPage.getVehicleType(), "4x4", "Vehicle Type");
		asserts.assertEquals(policyInfoPage.getCoverage(), "US$35,000.00", "Coverage");
		asserts.assertEquals(policyInfoPage.getCurrency(), "US$", "Currency");
		asserts.assertEquals(policyInfoPage.getTotalPrice(), 26.68, 1.33, "Total Price");
		asserts.assertAll();

	}
	
	@Test(enabled = true)
	public void verifyPolicyInfoForUSA4x4VehicleForDurationOf6Days() {

		SoftAssert asserts = new SoftAssert();

		rcLandingPage = new RentalCoverLandingPage(driver);
		policyInfoPage = rcLandingPage.getPolicyQuoteForVehicleRentalForDuration("United States","4x4", 4);

		asserts.assertEquals(policyInfoPage.getDestinationCountry(), "United States", "Destination Country");
		asserts.assertEquals(policyInfoPage.getVehicleType(), "4x4", "Vehicle Type");
		asserts.assertEquals(policyInfoPage.getCoverage(), "US$35,000.00", "Coverage");
		asserts.assertEquals(policyInfoPage.getCurrency(), "US$", "Currency");
		asserts.assertEquals(policyInfoPage.getTotalPrice(), 91.89, 1.00, "Total Price");
		asserts.assertAll();
	}
}