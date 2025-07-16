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

		produto = new Produto(
				"serum_facial_madura.webp",
				"Serum Facial para Pele Madura",
				"serum_facial_madura",
				"Sérum Facial para Peles Maduras  - Produzido com o Oleo Vegetal de Rosa Mosqueta, Semente de Uva, Germen de Trigo e Vitamina E. Auxilia a regeneracao da membrana celular e fornece Vitamina A natural para pele. Com oleos essenciais de Gerânio, Palmarosa e Ylang Ylang que tonificam e revigoram a pele madura.",
				true,
				30,
				BigDecimal.valueOf(55.00),
				LocalDate.of(2023, 4, 26),
				home_decor);
		produtoRepository.save(produto);

		produto = new Produto(
				"serum_pele_oleosa.webp",
				"Serum Facial para Pele Oleosa",
				"serum_pele_oleosa",
				"Sérum Facial para Peles Oleosas - Produzido com o Óleo Vegetal de Jojoba, Óleo Vegetal de Semente de Uva e Óleo Vegetal de Rosa Mosqueta. Auxilia a regular a oleosidade da pele, mantendo-a hidratada e saudável. Com óleos essenciais de Tea Tree, Lavanda e Limão Siciliano que ajudam a controlar a oleosidade e a acne.",
				true,
				30,
				BigDecimal.valueOf(49.00),
				LocalDate.of(2023, 4, 26),
				home_decor);
		produtoRepository.save(produto);

			produto = new Produto(
				"balm_labial_stick.webp",
				"Balm Labial Stick",
				"balm_labial_stick",
				"Balm Labial Stick - Produzido com Manteiga de Karité, Óleo Vegetal de Abacate e Cera de Abelha. Hidrata e protege os lábios, deixando-os macios e saudáveis. Com óleos essenciais de Menta e Limão Siciliano que proporcionam uma sensação refrescante e revigorante.",
				true,
				15,
				BigDecimal.valueOf(20.00),
				LocalDate.of(2023, 4, 26),
				hidratante);
		produtoRepository.save(produto);

			produto = new Produto(
				"escalda_pes.webp",
				"Escalda Pés Relaxante",
				"escalda_pes",
				"Escalda Pés Relaxante - Produzido com Sal Grosso, Óleo Vegetal de Amêndoas Doces e Óleos Essenciais de Lavanda e Eucalipto. Proporciona um momento de relaxamento e alívio para os pés cansados, ajudando a reduzir o inchaço e a fadiga. Ideal para ser usado após um longo dia de trabalho ou atividades físicas. Basta dissolver o produto em água morna e mergulhar os pés por alguns minutos para sentir o alívio e o relaxamento.",
				true,
				20,
				BigDecimal.valueOf(35.00),
				LocalDate.of(2023, 4, 26),
				sabonete);
		produtoRepository.save(produto);
		
			produto = new Produto(
				"difusores_ambiente.webp",
				"Difusores de Ambiente",
				"difusores_ambiente",
				"Difusores de Ambiente - Produzidos com óleos essenciais naturais e álcool de cereais, os difusores de ambiente são uma forma prática e elegante de perfumar o ambiente. Com uma variedade de fragrâncias, eles proporcionam uma experiência sensorial única e duradoura. Basta colocar os palitos de madeira no frasco e deixar que o aroma se espalhe pelo ambiente. Ideal para ser usado em casa, no escritório ou em qualquer lugar que precise de um toque especial de frescor e bem-estar. Disponíveis em diferentes tamanhos e fragrâncias, esses difusores são perfeitos para criar uma atmosfera acolhedora e relaxante.",
				true,
				100,
				BigDecimal.valueOf(60.00),
				LocalDate.of(2023, 4, 26),
				home_decor);
		produtoRepository.save(produto);

			produto = new Produto(
				"desodorante.webp",
				"Desodorante Natural",
				"desodorante_natural",
				"Desodorante Natural - Produzido com Óleo Vegetal de Coco, Bicarbonato de Sódio e Óleos Essenciais de Lavanda e Tea Tree. Proporciona uma proteção eficaz contra odores, sem agredir a pele. Com uma fórmula livre de alumínio e parabenos, é uma opção saudável e sustentável para quem busca um desodorante natural. A combinação dos óleos essenciais proporciona uma sensação refrescante e revigorante, mantendo a pele seca e confortável ao longo do dia. Ideal para ser usado diariamente, garantindo proteção e conforto sem comprometer a saúde da pele.",
				true,
				50,
				BigDecimal.valueOf(30.00),
				LocalDate.of(2023, 4, 26),
				hidratante);
		produtoRepository.save(produto);

			produto = new Produto(
				"esponja.webp",
				"Esponja Vegetal",
				"esponja_vegetal",
				"Esponja Vegetal - Feita a partir da fibra natural do luffa, a esponja vegetal é uma opção ecológica e sustentável para esfoliação da pele. Com propriedades esfoliantes, ela ajuda a remover as células mortas e a estimular a circulação sanguínea, deixando a pele mais suave e renovada. Ideal para ser usada no banho, a esponja vegetal é uma alternativa saudável e amiga do meio ambiente aos produtos sintéticos.",
				true,
				30,
				BigDecimal.valueOf(15.00),
				LocalDate.of(2023, 4, 26),
				sabonete);
		produtoRepository.save(produto);

			produto = new Produto(
				"sinergia_foco.webp",
				"Sinergia Foco",
				"sinergia_foco",
				"Sinergia Foco - Produzida com óleos essenciais de Alecrim, Hortelã-Pimenta e Limão Siciliano. Proporciona uma sensação de clareza mental e concentração, ideal para momentos de estudo ou trabalho. Com uma fórmula 100% natural, a Sinergia Foco é uma opção saudável e eficaz para quem busca aumentar a produtividade e o foco.",
				true,
				10,
				BigDecimal.valueOf(45.00),
				LocalDate.of(2023, 4, 26),
				home_decor);
		produtoRepository.save(produto);

			produto = new Produto(
				"sinergia_enxaqueca.jpg",
				"Sinergia Enxaqueca",
				"sinergia_enxaqueca",
				"Sinergia Enxaqueca - Produzida com óleos essenciais de Lavanda, Hortelã-Pimenta e Eucalipto. Proporciona alívio para dores de cabeça e enxaquecas, ajudando a relaxar e aliviar a tensão. Com uma fórmula 100% natural, a Sinergia Enxaqueca é uma opção saudável e eficaz para quem busca alívio para dores de cabeça e enxaquecas.",
				true,
				10,
				BigDecimal.valueOf(45.00),
				LocalDate.of(2023, 4, 26),
				home_decor);
		produtoRepository.save(produto);

			produto = new Produto(
				"sabonete_macela.webp",
				"Sabonete de Macela",
				"sabonete_macela",
				"Sabonete de Macela - Produzido com extrato de macela, um ingrediente natural conhecido por suas propriedades calmantes e anti-inflamatórias. Auxilia no tratamento de irritações e alergias na pele, proporcionando alívio e conforto. Com uma fórmula suave e delicada, o sabonete de macela é ideal para peles sensíveis e propensas a irritações. Ele ajuda a acalmar a pele, reduzindo vermelhidão e coceira, deixando-a mais suave e saudável.",
				true,
				30,
				BigDecimal.valueOf(23.00),
				LocalDate.of(2023, 4, 26),
				sabonete);
		produtoRepository.save(produto);

			produto = new Produto(
				"kit_home.webp",
				"Kit Home",
				"kit_home",
				"Kit Home - Um conjunto especial de produtos para transformar sua casa em um lar acolhedor e perfumado. Inclui um difusor de ambiente, um home spray e um sachê perfumado, todos com fragrâncias exclusivas e naturais. O difusor de ambiente proporciona uma liberação contínua de aroma, enquanto o home spray é ideal para borrifar em roupas de cama, cortinas e sofás. O sachê perfumado é perfeito para gavetas, armários e bolsas, deixando tudo com um cheiro agradável e duradouro.",
				true,
				100,
				BigDecimal.valueOf(120.00),
				LocalDate.of(2023, 4, 26),
				home_decor);
		produtoRepository.save(produto);



		

	}
}
