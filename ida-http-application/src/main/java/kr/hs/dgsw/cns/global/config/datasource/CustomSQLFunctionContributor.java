package kr.hs.dgsw.cns.global.config.datasource;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

@SuppressWarnings("unused")
public class CustomSQLFunctionContributor implements MetadataBuilderContributor {

    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        metadataBuilder.applySqlFunction("GROUP_CONCAT",
                new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
