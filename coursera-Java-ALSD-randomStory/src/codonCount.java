import java.util.HashMap;

public class codonCount {

	private HashMap<String,Integer> codons;
	codonCount(){
		codons = new HashMap<>();
	}
	
	void buildCodonMap(int start, String dna) {
		String dnaStrand = dna.trim();
		for(int i = start; i < dnaStrand.length()-2; i+=3) {
			String codon = dnaStrand.substring(i, i+3);
			if(codons.keySet().contains(codon)) {
				codons.put(codon,codons.get(codon)+1);
			}
			else {
				codons.put(codon,1);
			}
		}
		for(String s: codons.keySet()) {
			System.out.println(s+"\t"+codons.get(s));
		}
	}
	
	String getMostCommonCodon() {
		return null;
	}
	public static void main(String[] args) {
		codonCount cc = new codonCount();
		cc.buildCodonMap(0, "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC");
	}

}
