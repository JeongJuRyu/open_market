package com.tmax.cm.superstore.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitForeignKeyNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.springframework.stereotype.Component;

/**
 * Hibernate의 이름 자동 생성(테이블명, 컬럼명, 외래키명, ...) 오버라이드
 */
@Component
public class HibernateImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

    /**
     * 외래키 이름 자동 생성 방법 오버라이드
    */
    @Override
    public Identifier determineForeignKeyName(ImplicitForeignKeyNameSource source) {

        Identifier userProvidedIdentifier = source.getUserProvidedIdentifier();

        // 엔티티에 @ForeignKey의 name으로 이름을 명시적으로 지정한 경우 자동 이름 생성 건너뛴다
        if (userProvidedIdentifier != null) {
            return userProvidedIdentifier;
        }

        StringBuilder stringBuilder = new StringBuilder()
                .append("FK_").append(source.getTableName());

        for (Identifier columnName : source.getColumnNames()) {
            stringBuilder.append("_").append(columnName);
        }

        String fkName = stringBuilder.toString();

        return this.toIdentifier(fkName, source.getBuildingContext());
    }
}
