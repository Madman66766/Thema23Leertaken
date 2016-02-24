import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class GetalRij {
	private int[] getallen;
	
	public GetalRij( int aantal, int max ){
		// Belangrijke aanname: aantal < max, anders kunnen de getallen niet uniek zijn.
		getallen = new int[aantal];
		vulArrayMetUniekeWaarden( aantal, max );
	}

	private void vulArrayMetUniekeWaarden(int aantal, int max) {
		// Vul een hulplijst met getallen 0, ..., max
		ArrayList hulpLijst = new ArrayList( max );
		for ( int i = 0; i < max; i++){
			hulpLijst.add( i );
		}
		
		// Stop 'aantal' random waarden in getallen
		Random r = new Random();
		for ( int i = 0; i <aantal; i++){
			// Het omzetten van Integer naar int gaat sinds Java 1.5 automatisch (unboxing).
			int getal = (Integer) (hulpLijst.remove( r.nextInt( hulpLijst.size())));
			getallen[i] = getal;
		}
	}
	
	public boolean zitErinA( int zoekWaarde ){
		boolean found = false;
		for (int i = 0; i < getallen.length - 1; i++){
			if (getallen[i] == zoekWaarde){
				found = true;
			}
		}
		return found;
	}

	public boolean zitErinB( int zoekWaarde ){

		for (int i = 0; i < getallen.length - 1; i++){
			if (getallen[i] == zoekWaarde){
				return true;
			}
		}
		return false;
	}

	public boolean zitErinC( int zoekWaarde ){
		sorteer();
		return zitErinB(zoekWaarde);
	}

	public boolean zitErinD( int zoekWaarde ){
		sorteer();

		int min = 0, max = getallen.length - 1;
		while (min <= max) {
			int mid = min + ((max - min) /2);
			if (getallen[mid] == zoekWaarde){
				return true;
			} else if (getallen[mid] < zoekWaarde) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		return false;
	}
	
	public void sorteer(){
		Arrays.sort( getallen);
	}
	
	public void print(){
		for( int i = 0; i < getallen.length; i++)
			System.out.println(getallen[i]);
	}

}
