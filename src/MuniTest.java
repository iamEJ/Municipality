import lt.vtvpmc.municipality.AbstractMunicipalityTest;
import lt.vtvpmc.municipality.Municipality;

public class MuniTest extends AbstractMunicipalityTest{

	@Override
	public Municipality getMunicipality() {
		// TODO Auto-generated method stub
		return new Muni();
	}

}
