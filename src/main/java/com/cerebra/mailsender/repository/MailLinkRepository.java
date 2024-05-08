package com.cerebra.mailsender.repository;

import com.cerebra.mailsender.enums.MailStatus;
import com.cerebra.mailsender.model.MailLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MailLinkRepository extends JpaRepository<MailLink,Long> {


    List<MailLink>  findByMailId(Long mailId);

    @Modifying
    @Query("""
            update MailLink ml set ml.clickCount= :clickCount where ml.mail.id= :mailId and ml.url= :url
            """)
    void updateClickedCountForLinks(@Param("clickCount")Integer clickCount,
                                    @Param("mailId")Long mailId,
                                    @Param("url")String url);



}
