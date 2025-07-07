package com.carlosribeiro.apirestfulv1;

import com.carlosribeiro.apirestfulv1.model.Categoria;
import com.carlosribeiro.apirestfulv1.model.Produto;
import com.carlosribeiro.apirestfulv1.model.Usuario;
import com.carlosribeiro.apirestfulv1.repository.CategoriaRepository;
import com.carlosribeiro.apirestfulv1.repository.ProdutoRepository;
import com.carlosribeiro.apirestfulv1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:5173");
			}
		};
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

		Categoria shampoo = new Categoria("Shampoo", "shampoos");
		categoriaRepository.save(shampoo);

		Produto produto = new Produto(
				"hidratante_corporal.png",
				"Hidratante Corporal",
				"hidratante_corporal",
				"Fórmula suave, nutritiva, com consistência cremosa e rápida absorção. Ideal para todos os tipos de pele. Contém ativos antioxidantes que auxiliam na hidratação e regeneração da pele. Principais ativos: Manteiga de Manga, Óleo Vegetal de Castanha do Pará, Óleo Vegetal de Pracaxi, Cera de Coco, Glicerina Vegetal, Lactado de Sódio, Vitamina E, água desmineralizada, Azeite de oliva, Óleos essenciais e Oleoresina Alecrim. ",
				true,
				500,
				BigDecimal.valueOf(41.60),
				LocalDate.of(2023, 4, 26),
				hidratante);
		produtoRepository.save(produto);

		produto = new Produto(
				"shampoo_dolomita.png",
				"Shampoo de Dolomita",
				"shampoo_dolomita",
				"O Shampoo de Dolomita é um produto inovador que combina a pureza da dolomita com ingredientes naturais para proporcionar uma limpeza suave e eficaz aos cabelos. A dolomita, um mineral rico em magnésio e cálcio, é conhecida por suas propriedades benéficas para a saúde capilar. Este shampoo é formulado para nutrir, fortalecer e revitalizar os fios, deixando-os macios, brilhantes e saudáveis.",
				true,
				30,
				BigDecimal.valueOf(38.00),
				LocalDate.of(2023, 4, 26),
				shampoo);
		produtoRepository.save(produto);

		produto = new Produto(
				"shampoo_spirulina.png",
				"Shampoo de Spirulina",
				"shampoo_spirulina",
				"O Shampoo de Spirulina é um produto inovador que combina a poderosa alga spirulina com ingredientes naturais para proporcionar uma limpeza suave e eficaz aos cabelos. A spirulina, rica em proteínas, vitaminas e minerais, é conhecida por suas propriedades benéficas para a saúde capilar. Este shampoo é formulado para nutrir, fortalecer e revitalizar os fios, deixando-os macios, brilhantes e saudáveis.",
				true,
				30,
				BigDecimal.valueOf(38.00),
				LocalDate.of(2023, 4, 26),
				shampoo);
		produtoRepository.save(produto);

		produto = new Produto(
				"shampoo_detox.png",
				"Shampoo Detox",
				"shampoo_detox",
				"Um Shampoo de limpeza profunda que renova a saúde dos cabelos, controla a oleosidade e remove resíduos sem desidratar os fios. Ele é RECOMENDADO PARA TODOS OS TIPOS DE CABELOS. Possui em sua composição o Carvão Ativado e a Argila preta eles agem como removedor de toxinas e auxilia no crescimento dos fios, já o Óleo essencial de Menta refresca e ajuda no combate à descamação excessiva do couro cabeludo (caspa).",
				true,
				30,
				BigDecimal.valueOf(38.00),
				LocalDate.of(2023, 4, 26),
				shampoo);
		produtoRepository.save(produto);

		produto = new Produto(
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
				"home_spray.png",
				"Home Spray",
				"home_spray",
				"Desfrute de ambientes cheirosos e tenha uma experiência única e marcante com nossas coleções de Home Spray com perfumes exclusivos da linha de produtos para casa.. São diversas fragrâncias desenvolvidas especialmente para conquistar ambientes mais íntimos, românticos ou urbanos.",
				true,
				80,
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
				"sabonete_capim_limao.png",
				"Sabonete de Capim Limão",
				"sabonete_capim_limao",
				"Auxilia na limpeza profunda da pele, combatendo impurezas e bactérias. Ajuda a proteger a pele contra os danos dos radicais livres. O aroma suave do capim limão proporciona uma sensação de frescor e tranquilidade.",
				true,
				42,
				BigDecimal.valueOf(23.00),
				LocalDate.of(2023, 6, 29),
				sabonete);
		produtoRepository.save(produto);

		produto = new Produto(
				"sabonete_pimenta_rosa.png",
				"Sabonete de Pimenta Rosa",
				"sabonete_pimenta_rosa",
				"Quer cuidar da sua pele com um toque natural e eficaz? Nosso Sabonete de Pimenta Rosa com Óleo Essencial de Hortelã-Pimenta é a escolha perfeita para quem busca alívio e frescor no dia a dia. Com propriedades adstringentes, antissépticas e antifúngicas, ele auxilia no tratamento de dermatites, psoríase e outras irritações da pele.",
				true,
				260,
				BigDecimal.valueOf(22.31),
				LocalDate.of(2023, 6, 29),
				sabonete);
		produtoRepository.save(produto);

		produto = new Produto(
				"sabonete_camomila.png",
				"Sabonete de Camomila",
				"sabonete_camomila",
				"O Sabonete de Camomila é o cuidado diário que a sua pele merece. Rico em flavonoides e cumarina, ingredientes naturais com propriedades calmantes e cicatrizantes, ele proporciona uma limpeza profunda e suave, respeitando o equilíbrio natural da pele Com ação hidratante e regeneradora, evita o ressecamento e melhora a textura da pele, deixando-a com um aspecto mais saudável, iluminado e jovem. Sua fórmula delicada auxilia no clareamento de manchas, promove a elasticidade e combate o aspecto seco e envelhecido.",
				true,
				300,
				BigDecimal.valueOf(23.00),
				LocalDate.of(2023, 8, 12),
				sabonete);
		produtoRepository.save(produto);

		produto = new Produto(
				"sabonete_maracuja.png",
				"Sabonete de Maracujá",
				"sabonete_maracuja",
				"Promove hidratação e cicatrização ao mesmo tempo. A adição de suas sementes na composição do produto, produz uma esfoliação leve e a esperada sensação de limpeza. Sua apresentação em duas camadas dá ao sabonete uma interessante lembrança da fruta.",
				true,
				150,
				BigDecimal.valueOf(23.00),
				LocalDate.of(2023, 8, 12),
				sabonete);
		produtoRepository.save(produto);

		produto = new Produto(
				"sabonete_canela.png",
				"Sabonete de Canela",
				"sabonete_canela",
				"Ele é afrodisíaco, estimulante, traz elasticidade e brilho à pele, e possui propriedades esfoliantes. Por conter alto teor de antioxidantes, ela é excelente no combate ao envelhecimento da cutis.",
				true,
				255,
				BigDecimal.valueOf(23.00),
				LocalDate.of(2023, 4, 2),
				sabonete);
		produtoRepository.save(produto);

		produto = new Produto(
				"sabonete_curcuma.png",
				"Sabonete de Curcuma",
				"sabonete_curcuma",
				"Com propriedades anti-inflamatórias e antioxidantes, ajuda no tratamento de doenças inflamatória como psoríase e dermatite atópica, este sabonete vai deixar a sua pele com uma aparência mais jovem e saudável.",
				true,
				25,
				BigDecimal.valueOf(23.00),
				LocalDate.of(2023, 4, 2),
				sabonete);
		produtoRepository.save(produto);

		produto = new Produto(
				"sabonete_carvao_argila.png",
				"Sabonete de Carvão e Argila",
				"sabonete_carvao_argila",
				"Auxilia na limpeza profunda da pele, combatendo impurezas e bactérias. Ajuda a proteger a pele contra os danos dos radicais livres. O aroma suave do capim limão proporciona uma sensação de frescor e tranquilidade.",
				true,
				500,
				BigDecimal.valueOf(23.00),
				LocalDate.of(2023, 5, 22),
				sabonete);
		produtoRepository.save(produto);

		produto = new Produto(
				"vela_aromatica.png",
				"Vela Aromática",
				"vela_aromatica",
				"Vela aromática feita com cera de soja, pavio de madeira e óleos essenciais naturais. Proporciona uma experiência sensorial única, com aromas envolventes que criam um ambiente acolhedor e relaxante.",
				true,
				100,
				BigDecimal.valueOf(45.00),
				LocalDate.of(2023, 4, 26),
				velas);
		produtoRepository.save(produto);

	}
}
