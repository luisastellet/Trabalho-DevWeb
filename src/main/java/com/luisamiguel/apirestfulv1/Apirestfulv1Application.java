package com.luisamiguel.apirestfulv1;

import com.luisamiguel.apirestfulv1.model.Categoria;
import com.luisamiguel.apirestfulv1.model.Produto;
import com.luisamiguel.apirestfulv1.model.Usuario;
import com.luisamiguel.apirestfulv1.repository.CategoriaRepository;
import com.luisamiguel.apirestfulv1.repository.ProdutoRepository;
import com.luisamiguel.apirestfulv1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class Apirestfulv1Application implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Apirestfulv1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario usuario = new Usuario("admin", "desweb");
		usuarioRepository.save(usuario);

		Categoria sabonete = new Categoria("Sabonete", "sabonetes");
		categoriaRepository.save(sabonete);

		Categoria hidratante = new Categoria("Hidratante", "hidratantes");
		categoriaRepository.save(hidratante);

		Categoria velas = new Categoria("Vela aromática", "velas-aromaticas");
		categoriaRepository.save(velas);

		Categoria home_decor = new Categoria("Home & Decor", "home-decor");
		categoriaRepository.save(home_decor);

		Produto produto = new Produto(
				"saches-perfumados.png",
				"Sachê Perfumado",
				"sache_perfumado",
				"Nossos sachês perfumados são cuidadosamente elaborados com essência premium vermiculita, um mineral que potencializa a fixação e a durabilidade das fragrâncias, liberando o aroma de forma suave e contínua por semanas. Perfeitos para perfumar gavetas, closets, bolsas, carros ou qualquer cantinho especial, eles proporcionam bem-estar e aconchego ao ambiente, transformando a rotina com os benefícios da aromaterapia.",
				true,
				100,
				BigDecimal.valueOf(27.90),
				LocalDate.of(2023, 4, 26),
				home_decor);
		produtoRepository.save(produto);

		produto = new Produto(
				"sabonete_arroz_lavanda.png",
				"Sabonete de Arroz e Lavanda",
				"sabonete_arroz_lavanda",
				"Experimente o poder da natureza com nosso Sabonete Natural de Arroz e Lavanda, formulado para proporcionar um cuidado suave e eficaz para a sua pele. Com uma combinação única de ingredientes naturais, esse sabonete oferece benefícios incríveis, como:",
				true,
				500,
				BigDecimal.valueOf(23.00),
				LocalDate.of(2023, 5, 22),
				sabonete);
		produtoRepository.save(produto);

		produto = new Produto(
				"sabonete_carvao_argila.png",
				"Sabonete de Carvão e Argila",
				"sabonete_carvao_argila",
				"Um cuidado especial para a sua pele, direto da natureza.\n" + //
				"Nosso sabonete natural combina o poder desintoxicante do carvão ativado com a ação purificante da argila preta, promovendo uma limpeza profunda e eficaz.\n" + //
				"\n" + //
				"🌿 Indicado para peles mistas, oleosas, com manchas e acne.\n" + //
				"Pode ser usado tanto no rosto quanto no corpo, deixando a pele com uma sensação fresca e renovada.\n" + //
				"\n" + //
				"✨ Possui um aroma floral suave e equilibrado, graças à combinação dos óleos essenciais de gerânio e ginseng.",
				true,
				500,
				BigDecimal.valueOf(23.00),
				LocalDate.of(2023, 5, 22),
				sabonete);
		produtoRepository.save(produto);

		produto = new Produto(
				"abobora.png",
				"Abóbora",
				"abobora",
				"1 unidade aprox. 1,9kg",
				true,
				400,
				BigDecimal.valueOf(4.7),
				LocalDate.of(2023, 3, 24),
				hidratante);
		produtoRepository.save(produto);

		produto = new Produto(
				"acelga.png",
				"Acelga",
				"acelga",
				"1 maço de aprox. 400g",
				true,
				120,
				BigDecimal.valueOf(4.99),
				LocalDate.of(2023, 3, 12),
				velas);
		produtoRepository.save(produto);

		produto = new Produto(
				"agriao.png",
				"Agrião",
				"agriao",
				"1 maço de aprox. 200g",
				true,
				340,
				BigDecimal.valueOf(2.5),
				LocalDate.of(2023, 5, 17),
				velas);
		produtoRepository.save(produto);

		produto = new Produto(
				"alface.png",
				"Alface",
				"alface",
				"1 maço de aprox. 200g",
				true,
				220,
				BigDecimal.valueOf(4.99),
				LocalDate.of(2023, 5, 14),
				velas);
		produtoRepository.save(produto);

		produto = new Produto(
				"banana.png",
				"Banana",
				"banana",
				"1 unidade aprox. 165g",
				true,
				350,
				BigDecimal.valueOf(1.05),
				LocalDate.of(2023, 2, 22),
				sabonete);
		produtoRepository.save(produto);

		produto = new Produto(
				"beringela.png",
				"Beringela",
				"beringela",
				"1 unidade aprox. 370g",
				true,
				720,
				BigDecimal.valueOf(1.85),
				LocalDate.of(2023, 2, 23),
				hidratante);
		produtoRepository.save(produto);

		produto = new Produto(
				"brocolis.png",
				"Brócolis",
				"brocolis",
				"1 unidade aprox. 300g",
				true,
				600,
				BigDecimal.valueOf(5.39),
				LocalDate.of(2023, 3, 28),
				velas);
		produtoRepository.save(produto);

		produto = new Produto(
				"cebola.png",
				"Cebola",
				"cebola",
				"1 unidade aprox. 200g",
				true,
				95,
				BigDecimal.valueOf(0.56),
				LocalDate.of(2023, 4, 30),
				hidratante);
		produtoRepository.save(produto);

		produto = new Produto(
				"cenoura.png",
				"Cenoura",
				"cenoura",
				"1 unidade aprox. 180g",
				true,
				350,
				BigDecimal.valueOf(1.01),
				LocalDate.of(2023, 5, 29),
				hidratante);
		produtoRepository.save(produto);

		produto = new Produto(
				"cereja.png",
				"Cereja",
				"cereja",
				"1 unidade aprox. 250g",
				true,
				240,
				BigDecimal.valueOf(11.23),
				LocalDate.of(2023, 5, 11),
				sabonete);
		produtoRepository.save(produto);
	}
}
