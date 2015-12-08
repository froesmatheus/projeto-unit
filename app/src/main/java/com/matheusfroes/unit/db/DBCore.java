package com.matheusfroes.unit.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.matheusfroes.unit.model.Question;

import java.util.ArrayList;
import java.util.List;

import static com.matheusfroes.unit.model.Question.ALTERNATIVE_A;
import static com.matheusfroes.unit.model.Question.ALTERNATIVE_B;
import static com.matheusfroes.unit.model.Question.ALTERNATIVE_C;
import static com.matheusfroes.unit.model.Question.ALTERNATIVE_D;
import static com.matheusfroes.unit.model.Question.ALTERNATIVE_E;

/**
 * Created by Matheus on 02/10/2015.
 */
public class DBCore extends SQLiteOpenHelper {
    public static final String TABELA_PERGUNTAS = "Perguntas";
    // Colunas da tabela Perguntas
    public static final String COLUNA_DISCIPLINAS_ID = "_id";
    public static final String COLUNA_ENUNCIADO = "enunciado";
    public static final String COLUNA_ALTERNATIVA_A = "alternativa_a";
    public static final String COLUNA_ALTERNATIVA_B = "alternativa_b";
    public static final String COLUNA_ALTERNATIVA_C = "alternativa_c";
    public static final String COLUNA_ALTERNATIVA_D = "alternativa_d";
    public static final String COLUNA_ALTERNATIVA_E = "alternativa_e";
    public static final String COLUNA_ALTERNATIVA_CORRETA = "alternativa_correta";
    public static final String COLUNA_EXPLICACAO = "explicacao";
    public static final String COLUNA_ALTERNATIVA_CORRETA_ID = "alternativa_correta_id";
    private static final String NOME_BANCO = "FACE";
    private static final int VERSAO_BANCO = 1;
    private static final String CREATE_TABLE_PERGUNTAS = "CREATE TABLE " + TABELA_PERGUNTAS + "(" +
            COLUNA_DISCIPLINAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUNA_ENUNCIADO + " TEXT NOT NULL," +
            COLUNA_ALTERNATIVA_A + " TEXT NOT NULL," +
            COLUNA_ALTERNATIVA_B + " TEXT NULL, " +
            COLUNA_ALTERNATIVA_C + " TEXT NOT NULL, " +
            COLUNA_ALTERNATIVA_D + " TEXT NOT NULL, " +
            COLUNA_ALTERNATIVA_E + " TEXT NOT NULL, " +
            COLUNA_ALTERNATIVA_CORRETA + " TEXT NOT NULL, " +
            COLUNA_EXPLICACAO + " TEXT NOT NULL, " +
            COLUNA_ALTERNATIVA_CORRETA_ID + " INTEGER NOT NULL" +
            ");";
    private Context context;

    public DBCore(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERGUNTAS);

        List<Question> defaultQuestionList = insertDefaultQuestions();
        for (int i = 0; i < 10; i++) {
            ContentValues contentValues = new ContentValues();

            Question question = defaultQuestionList.get(i);

            contentValues.put(DBCore.COLUNA_ENUNCIADO, question.getQuestionTitle());
            contentValues.put(DBCore.COLUNA_ALTERNATIVA_A, question.getA());
            contentValues.put(DBCore.COLUNA_ALTERNATIVA_B, question.getB());
            contentValues.put(DBCore.COLUNA_ALTERNATIVA_C, question.getC());
            contentValues.put(DBCore.COLUNA_ALTERNATIVA_D, question.getD());
            contentValues.put(DBCore.COLUNA_ALTERNATIVA_E, question.getE());
            contentValues.put(DBCore.COLUNA_ALTERNATIVA_CORRETA, question.getRightAnswer());
            contentValues.put(DBCore.COLUNA_EXPLICACAO, question.getExplanation());
            contentValues.put(DBCore.COLUNA_ALTERNATIVA_CORRETA_ID, question.getRightAnswerPosition());

            db.insert(TABELA_PERGUNTAS, null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABELA_PERGUNTAS);
        onCreate(db);
    }

    public List<Question> insertDefaultQuestions() {
        List<Question> defaultQuestionList = new ArrayList<>();

        defaultQuestionList.add(new Question(
                "O que é uma aceleradora?",
                "É uma empresa de consultoria de gestão de negócios que orienta startups a tirar sua ideia do papel.",
                "São espaços compartilhados de trabalho, onde as startups podem dividir o mesmo espaço e trocar experiências",
                "São grandes empresas privadas que usam as startups para gerar novas soluções e inovar em seu segmento.",
                "É uma entidade que presta serviços à startups selecionadas com ações para desenvolver essas empresas de forma mais rápida.",
                "Conjunto de investidores que investem capital em startups",
                "Prestam serviços à startups selecionadas com ações para desenvolver essas empresas de forma mais rápida.",
                "As aceleradoras tem um importante papel no desenvolvimento das startups, para consolidação do seu modelo de negócios, ampliação de mercado e promoção do seu crescimento para alcançar nível global.",
                ALTERNATIVE_D));

        defaultQuestionList.add(new Question(
                "O que é pitch?",
                "É uma apresentação desenvolvida para mostrar aos potenciais investidores ou clientes o projeto de uma startup, normalmente em um curto espaço de tempo.",
                "É uma metodologia de desenvolvimento de ideias, a partir de ferramentas digitais.",
                "É um tipo de apresentação estruturada em 4 blocos de cerca de 20 minutos cada para expor as 4 principais informações da startup.",
                "É o profissional responsável pela condução de apresentações profissionais para startups que buscam investimento.",
                "É um lugar onde investidores se reunem para discutir em quais startups eles investirão",
                "É uma apresentação desenvolvida para mostrar aos potenciais investidores ou clientes o projeto de uma startup, normalmente em um curto espaço de tempo.",
                "O pitch pode ajudar a startup a ganhar visibilidade, adquirir novos clientes e investidores para seu negócios em uma apresentação rápida com as principais informações sobre sua ideia ou negócio.",
                ALTERNATIVE_A));

        defaultQuestionList.add(new Question(
                "O que é um investidor anjo?",
                "Pessoa responsável por criar o plano de negócios da empresa",
                "Pessoa física que faz investimentos com seu próprio capital em empresas nascentes",
                "Pessoa responsável por fiscalizar as atividades da empresa",
                "Pessoa que compra ações de uma empresa",
                "Responsável por gerir os investimentos vindo de fora na empresa",
                "Pessoa física que faz investimentos com seu próprio capital em empresas nascentes",
                "O Investidor-Anjo é normalmente um (ex-)empresário/empreendedor ou executivo que já trilhou uma carreira de sucesso, acumulando recursos suficientes para alocar uma parte (normalmente entre 5% a 10% do seu patrimônio) para investir em novas empresas, bem como aplicar sua experiência apoiando a empresa.",
                ALTERNATIVE_B));

        defaultQuestionList.add(new Question(
                "O que é um MVP?",
                "Sigla em inglês para Much Value Product - que significa ter um produto muito valioso",
                "Sigla em inglês para Many Variety of Product - que significa ter muitos produtos variados",
                "Sigla para Multinacional, Valioso e Produtivo, que são características de uma startup",
                "Sigla para Multicultural, Varejista e Participativo, que são características de um empreendedor",
                "Sigla em inglês para Minimum Viable Product - que significa ter um mínimo produto viável para testar",
                "Sigla em inglês para Minimum Viable Product - que significa ter um mínimo produto viável para testar",
                "O mínimo produto viável é usado pela startup para testar se a sua solução é bem aceita pelas pessoas, sem necessariamente já ter desenvolvido todas as funcionalidades da sua solução, fazendo com que ela aprenda como desenvolver o seu produto em um tempo menor e com custos menores também.",
                ALTERNATIVE_E));

        defaultQuestionList.add(new Question(
                "O que é um espaço de coworking?",
                "Um espaço compartilhado de trabalho, onde várias startups e empreendedores podem trabalhar e dividir a infra-estrutura do local",
                "Um ambiente inovador pertencente ao setor público para prestação de serviços de desburocratização para startups",
                "Um escritório de inovação que presta serviço de consultoria para startups",
                "Um espaço de prestação de serviços por meio de ferramentas digitais na internet",
                "Um espaço onde investidores se reunem para ajudar financeiramente startups",
                "Um espaço compartilhado de trabalho, onde várias startups e empreendedores podem trabalhar e dividir a infra-estrutura do local",
                "Os espaços de coworking costumam ser uma ótima oportunidade para as startups, pois além de poder dividir os custos de um escritório, nesse ambiente os empreendedores tem a oportunidade de compartilhar experiências e aprendizado.",
                ALTERNATIVE_A));

        defaultQuestionList.add(new Question(
                "O que é uma incubadora?",
                "É uma entidade que tem por objetivo comprar ações de startups para que as mesmas possam obter sucesso",
                "É um espaço compartilhado que as startups usam para reduzir os custos com aluguel",
                "É uma entidade que tem por objetivo oferecer suporte a empreendedores para que eles possam desenvolver ideias inovadoras e transformá-las em empreendimentos de sucesso.",
                "É um conjunto de empresas que ajuda startups a criar seu plano de negócios",
                "É uma empresa que oferece um espaço físico para que uma determinada startup não precise ter custos com aluguel",
                "É uma entidade que tem por objetivo oferecer suporte a empreendedores para que eles possam desenvolver ideias inovadoras e transformá-las em empreendimentos de sucesso.",
                "As Incubadoras de empresas são organizações que podem estar vinculadas as instituições de ensino públicas ou privadas, prefeituras, e até mesmo iniciativas empresariais independentes. A base de sustentação de um programa de incubação está alicerçada na difusão da cultura empreendedora, do conhecimento e da inovação.",
                ALTERNATIVE_C));

        defaultQuestionList.add(new Question(
                "O que é um Meetup?",
                "Encontro para fomentar o networking com a participação de empreendedores, investidores que possam contribuir para o amadurecimento do cenário local.",
                "Metodologia usada para o desenvolvimento de ideias inovadoras, através de ferramentas digitais.",
                "Local onde as pessoas trabalham coletivamente, dividindo o mesmo espaço e utilizando o mesmo equipamento.",
                "Grande palestra realizada por universidades para falar de temas relacionados a startups para os alunos de empreendedorismo.",
                "Local onde diversas startups realizam campeonatos para escolher qual tem a ideia mais criativa",
                "Encontro para fomentar o networking com a participação de empreendedores, investidores que possam contribuir para o amadurecimento do cenário local.",
                "O meetup normalmente é organizado por empreendedores que querem trazer para a sua localidade uma discussão rica sobre startups para levar conhecimento e mais preparo para os empreendedores locais e suas startups.",
                ALTERNATIVE_A));

        defaultQuestionList.add(new Question(
                "Qual dessas opções é mais importante para conseguir desenvolver uma startup de sucesso?",
                "Formação universitária, principalmente no curso de administração",
                "Conseguir investimento para desenvolver a ideia, porque para tirar uma ideia do papel é necessário muito recurso",
                "Ter uma ótima ideia, não contá-la para muitas pessoas para não correr o risco de alguém roubá-la",
                "Ter um grande conhecimento em negócios e não mostrar a ideia a outras startups",
                "Ter uma boa equipe que tenha uma ótima capacidade de execução, fazendo testes e validações da solução para melhorá-la aos poucos e entregar um produto que tenha boa aceitação no mercado.",
                "Ter uma boa equipe que tenha uma ótima capacidade de execução, fazendo testes e validações da solução para melhorá-la aos poucos e entregar um produto que tenha boa aceitação no mercado.",
                "Lembre-se que uma ideia não vale nada se não for tirada do papel. E para conseguir tirá-la do papel é importante ter uma boa equipe e desenvolver uma solução que tenha boa aceitação no mercado.",
                ALTERNATIVE_E));

        defaultQuestionList.add(new Question(
                "Qual das opções abaixo NÃO é uma característica de uma startup?",
                "Ter um modelo de negócios repetível",
                "Ter um plano de negócios com visão de longo prazo e abrangência local",
                "Ter alta capacidade de escala do negócios com crescimento rápido",
                "Ter uma solução muito inovadora",
                "Ter um plano de negócios que sofre muitas mudanças",
                "Ter um plano de negócios com visão de longo prazo e abrangência local",
                "As principais características de uma startups é ser muito inovadora, e ter um modelo negócios que seja repetível e escalável, ou seja, possa ser entregue a um grande número de pessoas, onde os custos crescem bem menos que a receita, o que faz com que ela consiga ter um crescimento rápido em um tempo menor que uma empresa tradicional.",
                ALTERNATIVE_B));

        defaultQuestionList.add(new Question(
                "O que é um mentor?",
                "É um jovem recém formado no curso de engenharia que ajuda outras empreendedores de startup a desenvolver o aspecto técnico do negócio.",
                "São consultores nas áreas de marketing e gestão que prestam serviço privado de orientação nas áreas em questão.",
                "São empreendedores de sucesso e outros profissionais do mercado, que tenham experiência e estão dispostos a ajudar outros empreendedores com o intuito de melhorar o desempenho do negócios e evitar erros.",
                "São jovens empreendedores que participaram de competições de startups e realizam palestras para compartilhar suas experiências.",
                "É um empreendedor que doa recursos para uma startup sem pedir nada em troca",
                "São empreendedores de sucesso e outros profissionais do mercado, que tenham experiência e estão dispostos a ajudar outros empreendedores com o intuito de melhorar o desempenho do negócios e evitar erros.",
                "Os mentores tem um papel muito importante no desenvolvimento das startups, pois o aprendizado a partir da experiência e erros de outras pessoas pode ajudar muito aquele empreendedor que está numa fase inicial do processo, além de fomentar o compartilhamento do conhecimento para potencializar o sucesso de novas startups naquele local.",
                ALTERNATIVE_C));

        return defaultQuestionList;
    }
}
