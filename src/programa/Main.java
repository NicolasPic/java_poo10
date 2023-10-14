package programa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Produto;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);		
		List<Produto> lista = new ArrayList<>();
		
		System.out.println("Digite o caminho do arquivo: ");
		String linkDoAq = sc.nextLine();
		
		File aqOrigem = new File(linkDoAq);
		String pastaOrigem = aqOrigem.getParent();
		
		boolean sucesso = new File(pastaOrigem + "\\out").mkdir();
		
		System.out.println("Pasta criada: " + sucesso);
		
		String aqDestino = aqOrigem + "\\out\\sumery.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(aqOrigem))){
			
			String itemCsv = br.readLine();
			while (itemCsv != null) {
				String[] campos = itemCsv.split(",");
				String nome = campos[0];
				double preco = Double.parseDouble(campos[1]);
				int quantidade = Integer.parseInt(campos[2]);
				
				lista.add(new Produto(nome,preco,quantidade));
				
				itemCsv = br.readLine();
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(aqDestino))) {
				
				for (Produto item: lista) {
					bw.write(item.getNome() + "," + String.format("%.2f", item.total()));
					bw.newLine();
				}
				
			} catch (IOException e){
				System.out.println("Error: " + e.getMessage());
			}
			
		} catch (IOException e) {
			System.out.println("Error :" + e.getMessage());
		}
		
		sc.close();

	}

}
