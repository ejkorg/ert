package com.onsemi.cim.apps.exensioreftables.ws.repository.lotg;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.onsemi.cim.apps.exensioreftables.ws.model.lotg.LotGInfo;
import com.onsemi.cim.apps.exensioreftables.ws.model.lotg.Status;

@Repository("LotGInfoCustomRepository")
public class LotgInfoCustomRepository {

    private static final Logger LOG = LoggerFactory.getLogger(LotgInfoCustomRepository.class);
    public static final String SQL_LOTG_VERSION = "v48";
    public static final String SQL_LOTG_QUERY = "/* src_tgt_xref_with converts obscure column names to something that makes more sense, and converts lot/parent date + time to datetime*/\r\n" + //
                "with src_tgt_xref_with as (\r\n" + //
                "   select /* +INLINE */ from_bank_code,\r\n" + //
                "          to_bank_code,\r\n" + //
                "          reversal_flag,\r\n" + //
                "          fk_genealogy_mafk as parent_part_id,\r\n" + //
                "          fk_genealogy_macla as parent_lot_class,\r\n" + //
                "          fk_genealogy_maide as parent_lot_num,\r\n" + //
                "          fk_genealogy_manod as parent_transdate,\r\n" + //
                "          fk_genealogy_manot as parent_transtime,\r\n" + //
                "          fk0genealogy_mafk as part_id,\r\n" + //
                "          fk0genealogy_macla as lot_class,\r\n" + //
                "          fk0genealogy_maide as lot_num,\r\n" + //
                "          fk1genealogy_manod as transdate,\r\n" + //
                "          fk1genealogy_manot as transtime,\r\n" + //
                "          post_date,\r\n" + //
                "          post_time,\r\n" + //
                "          to_char(\r\n" + //
                "             (cast(fk1genealogy_manod as timestamp) +(to_timestamp(substr(\r\n" + //
                "                fk1genealogy_manot,\r\n" + //
                "                1,\r\n" + //
                "                4\r\n" + //
                "             ),\r\n" + //
                "                  'HH24MI') - trunc(to_timestamp(substr(\r\n" + //
                "                fk1genealogy_manot,\r\n" + //
                "                1,\r\n" + //
                "                4\r\n" + //
                "             ),\r\n" + //
                "             'HH24MI')) +(cast(substr(\r\n" + //
                "                fk1genealogy_manot,\r\n" + //
                "                5,\r\n" + //
                "                4\r\n" + //
                "             ) as real) / 100) * interval '1' second)),\r\n" + //
                "             'YYYY-MM-DD HH24:MI:SS.FF'\r\n" + //
                "          ) as trans_dt,\r\n" + //
                "          to_char(\r\n" + //
                "             (cast(fk_genealogy_manod as timestamp) +(to_timestamp(substr(\r\n" + //
                "                fk_genealogy_manot,\r\n" + //
                "                1,\r\n" + //
                "                4\r\n" + //
                "             ),\r\n" + //
                "                  'HH24MI') - trunc(to_timestamp(substr(\r\n" + //
                "                fk_genealogy_manot,\r\n" + //
                "                1,\r\n" + //
                "                4\r\n" + //
                "             ),\r\n" + //
                "             'HH24MI')) +(cast(substr(\r\n" + //
                "                fk_genealogy_manot,\r\n" + //
                "                5,\r\n" + //
                "                4\r\n" + //
                "             ) as real) / 100) * interval '1' second)),\r\n" + //
                "             'YYYY-MM-DD HH24:MI:SS.FF'\r\n" + //
                "          ) as parent_trans_dt,\r\n" + //
                "          ( post_date + ( to_timestamp(substr(\r\n" + //
                "             post_time,\r\n" + //
                "             1,\r\n" + //
                "             4\r\n" + //
                "          ),\r\n" + //
                "                    'HH24MI') - trunc(to_timestamp(substr(\r\n" + //
                "             post_time,\r\n" + //
                "             1,\r\n" + //
                "             4\r\n" + //
                "          ),\r\n" + //
                "             'HH24MI')) + ( cast(substr(\r\n" + //
                "             post_time,\r\n" + //
                "             5,\r\n" + //
                "             4\r\n" + //
                "          ) as real) / 100 ) * interval '1' second ) ) as post_dt\r\n" + //
                "     from lotg_owner.src_tgt_xref\r\n" + //
                ")\r\n" + //
                "/* min_trans_date_lots finds the earliest transaction for a given lot ID*/,min_trans_date_lots as (\r\n" + //
                "   select *\r\n" + //
                "     from (\r\n" + //
                "      select mt.*,\r\n" + //
                "             dense_rank()\r\n" + //
                "             over(partition by lot_num\r\n" + //
                "                  order by\r\n" + //
                "                    case\r\n" + //
                "                       when ppi.part_type in('Wafer Fab Part') then\r\n" + //
                "                          1\r\n" + //
                "                       when ppi.part_type in('Wafer Post Fab Part') then\r\n" + //
                "                          2\r\n" + //
                "                       when ppi.part_type in('WDQ Part') then\r\n" + //
                "                          3\r\n" + //
                "                       when ppi.part_type in('Assembly Part') then\r\n" + //
                "                          4\r\n" + //
                "                       else\r\n" + //
                "                          5\r\n" + //
                "                    end,\r\n" + //
                "                    transdate,\r\n" + //
                "                    transtime\r\n" + //
                "             ) as lot_rank\r\n" + //
                "        from src_tgt_xref_with mt\r\n" + //
                "        left join lotg_owner.pc_item pi\r\n" + //
                "      on mt.part_id = pi.part_id\r\n" + //
                "        left join lotg_owner.pc_item ppi\r\n" + //
                "      on mt.parent_part_id = ppi.part_id\r\n" + //
                "        left join lotg_owner.mfg_bank_to_stage mbs\r\n" + //
                "      on mt.from_bank_code = mbs.bank_cd\r\n" + //
                "/* Don't get genealogy for substrate and epi material */\r\n" + //
                "       where ( pi.part_type is null\r\n" + //
                "          or pi.part_type not in ( 'Substrate Part',\r\n" + //
                "                                   'Ingot Part',\r\n" + //
                "                                   'PCB Substrate Part',\r\n" + //
                "                                   'Package Substrate' ) )\r\n" + //
                "-- Many sites have lot IDs that repeat on 10-year cycles.  Need to avoid colliding with those\r\n" + //
                "         and mt.transdate > sysdate - 365 * 10 * interval '1' day \r\n" + //
                "/* Exclude orion material */\r\n" + //
                "  --AND mt.PARENT_LOT_CLASS NOT IN (SELECT LOTCLASS_CD FROM LOTG_OWNER.LOT_CLASS WHERE DESCRIPTION like '%ORION%')\r\n" + //
                "/* Don't get reference/source lot data from bonused/merged transactions */\r\n" + //
                "         and not exists (\r\n" + //
                "         select 1\r\n" + //
                "           from src_tgt_xref_with vx\r\n" + //
                "          where vx.lot_num = mt.lot_num\r\n" + //
                "            and vx.parent_lot_num != mt.parent_lot_num\r\n" + //
                "            and vx.transdate = mt.transdate\r\n" + //
                "            and vx.transtime = mt.transtime\r\n" + //
                "            and vx.part_id = mt.part_id\r\n" + //
                "            and vx.parent_part_id = mt.parent_part_id\r\n" + //
                "      )\r\n" + //
                "/*Filter on lot here.  Need up to four lots: \r\n" + //
                "1. Original lot (no changes)\r\n" + //
                "2. Remove dot (.) if it exists\r\n" + //
                "3. Replace dot (.) with zero (0) \r\n" + //
                "4. *IF* lot ends with .\\d+[A-Z], remove dot and remove last character (e.g., RM12016.1F -> RM120161, RM12016.12Q -> RM1201612)\r\n" + //
                "*/\r\n" + //
                "-- Lot KG15Z1UXZAJ can't be traced back to KG15Z1UX in LOTG because the lot is converted from NF lot type to PS after it has already been split to KG15Z1UXZA\r\n" + //
                "-- Regression test lots: VN39B08T (L21480768/HAINA), RM120161 (RM12016/CZ4), LM5082940A (DA35166/ISMF), PN26QF1KX (NB6F7957/JND), N19S90M09 (N0S06040/SBN),\r\n" + //
                "--   21226TD9001.000 (21226TD9001/UV5),J2064102 (GAZ52111/USR),H2581001 (G73010/USU),H5979704 (GAY52079/USR),GZ020041 (H12746/USU), G0101701 (GA010170/USR),\r\n" + //
                "--   FQ839281 (NI11121/BE2), DP483621 (DP48362/ISMFAB), JM6377801 (JM637780/JND), RL874471 (RL87447/CZ4), J1393601 (GA003300/USR), KH25Y8TXAA (KH25Y8TX/KRH),\r\n" + //
                "--   KG224U0X (KG224U0X/KRG), KG246QUAA (KG246QUX/KRG), GZ020041 (H12746/USU), VN39B07M (L21480768/HAINA), VN34A00Q (JOV02933/VN5), L21381199 (LI2021248/HAINA)\r\n" + //
                "--   L21390053A (L21390054/HAINA), L21490187A (L21490187/HAINA), KG2589HXB (KG2589HX/KRG), KG268XMDA (KG268XMX/KRG), KG15Z1UXZAJ (KG15Z1UXZA/KRG), KG2363GXA (KG2363GX/KRG),\r\n" + //
                "--   KG0BGG1BA (KG0BGG1X/KRG), BJN500080 (F6F8881/TSMC), BJP070094 (J13672/USU), CPM2610067 (CPM26100/TSMC), J1121601 (E147301/JND), FR320561 (9563919/LFOUNDRY)\r\n" + //
                "--   FR312191 (9428919/UVA), H2060961 (H20609/XFAB), J5758201 (J12609/USU), RM2911760 (RM29117/CZ4), NL2137601 (NL21376/BE2), PA0H483901 (PA0H4839/JND),\r\n" + //
                "--   'J71901.1', 'J719011', 'J7190101', '190101' (N05VN204/VN6), G050141P07 (G050141P/JND), NK0627701 (NK06277/BE2)\r\n" + //
                "--/*:lotId, :lotIdWithoutDot, :lotIdWith0, substr(:lotIdWith0, 3, 50), :lotIdWithoutDotAndLettersInSuffix*/\r\n" + //
                "         and ( lot_num in ( :lotId,\r\n" + //
                "                            :lotIdWithoutDot,\r\n" + //
                "                            :lotIdWith0,\r\n" + //
                "                            substr(\r\n" + //
                "                             :lotIdWith0,\r\n" + //
                "                             3,\r\n" + //
                "                             50\r\n" + //
                "                          ),\r\n" + //
                "                            :lotIdWithoutDotAndLettersInSuffix )\r\n" + //
                "         and not ( lot_num = substr(\r\n" + //
                "            :lotIdWith0,\r\n" + //
                "            3,\r\n" + //
                "            50\r\n" + //
                "         )\r\n" + //
                "         and mbs.material_owner_cd not in ( 'CALA',\r\n" + //
                "                                            'CAR',\r\n" + //
                "                                            'LSH',\r\n" + //
                "                                            'SBN',\r\n" + //
                "                                            'SDC',\r\n" + //
                "                                            'SSL',\r\n" + //
                "                                            'SSMP' ) ) )\r\n" + //
                "         and reversal_flag = 'N'\r\n" + //
                "   ) tgt\r\n" + //
                "    where tgt.lot_rank = 1\r\n" + //
                ")\r\n" + //
                "--select * from min_trans_date_lots;\r\n" + //
                "/* starting_lots determines which lots to retrieve genealogy for*/,starting_lots as (\r\n" + //
                "   select lot_num,\r\n" + //
                "          lot_class,\r\n" + //
                "          part_id,\r\n" + //
                "          parent_lot_num,\r\n" + //
                "          parent_part_id,\r\n" + //
                "          v.parent_lot_class,\r\n" + //
                "          transdate,\r\n" + //
                "          transtime\r\n" + //
                "     from min_trans_date_lots v\r\n" + //
                "/* Exclude transactions where lot and part number don't change, except when it's a starting lot */\r\n" + //
                "    where not ( lot_num = parent_lot_num\r\n" + //
                "      and part_id = parent_part_id\r\n" + //
                "      and not exists (\r\n" + //
                "      select 1\r\n" + //
                "        from src_tgt_xref_with sl\r\n" + //
                "        left join lotg_owner.pc_item pi\r\n" + //
                "      on sl.parent_part_id = pi.part_id\r\n" + //
                "       where v.lot_num = sl.lot_num\r\n" + //
                "         and v.part_id = sl.part_id\r\n" + //
                "         and pi.part_type not in ( 'Substrate Part',\r\n" + //
                "                                   'Ingot Part',\r\n" + //
                "                                   'PCB Substrate Part',\r\n" + //
                "                                   'Package Substrate' )\r\n" + //
                "         and sl.parent_lot_class not in (\r\n" + //
                "         select lotclass_cd\r\n" + //
                "           from lotg_owner.lot_class\r\n" + //
                "          where description like '%ORION%'\r\n" + //
                "      )\r\n" + //
                "         and not exists (\r\n" + //
                "         select 1\r\n" + //
                "           from src_tgt_xref_with vx\r\n" + //
                "          where vx.lot_num = sl.lot_num\r\n" + //
                "            and vx.parent_lot_num != sl.parent_lot_num\r\n" + //
                "            and vx.transdate = sl.transdate\r\n" + //
                "            and vx.transtime = sl.transtime\r\n" + //
                "            and vx.part_id = sl.part_id\r\n" + //
                "            and vx.parent_part_id = sl.parent_part_id\r\n" + //
                "      )\r\n" + //
                "   ) )\r\n" + //
                "/* Exclude parent lots from the genealogy walk if they are parents in a merge transaction */\r\n" + //
                "      and not exists (\r\n" + //
                "      select 1\r\n" + //
                "        from src_tgt_xref_with vx\r\n" + //
                "       where vx.lot_num = v.lot_num\r\n" + //
                "         and vx.parent_lot_num != v.parent_lot_num\r\n" + //
                "         and vx.transdate = v.transdate\r\n" + //
                "         and vx.transtime = v.transtime\r\n" + //
                "         and vx.part_id = v.part_id\r\n" + //
                "         and vx.parent_part_id = v.parent_part_id\r\n" + //
                "   )\r\n" + //
                "      and v.lot_rank = 1\r\n" + //
                ")\r\n" + //
                "--select * from starting_lots;\r\n" + //
                ",starting_lots_ranked as (\r\n" + //
                "/* The initial lot_num filter tries several variations of the lot number to match the fab/manufacturing lot to the inventory lot */\r\n" + //
                "/* Give preferential treatment to inventory lots that most closely match the manufacturing lot. */\r\n" + //
                "/*  Those will be inventory lots containing the dot, or longer inventory lot IDs (no prefixes trimmed or characters removed) */\r\n" + //
                "   select s.*,\r\n" + //
                "          dense_rank()\r\n" + //
                "          over(\r\n" + //
                "              order by\r\n" + //
                "                case\r\n" + //
                "                   when regexp_like(lot_num,\r\n" + //
                "                                    '^.+\\.\\d$') then\r\n" + //
                "                      1\r\n" + //
                "                   when exists(\r\n" + //
                "                      select 1\r\n" + //
                "                        from starting_lots s2\r\n" + //
                "                       where regexp_like(s2.lot_num,\r\n" + //
                "                                         '..' || s.lot_num)\r\n" + //
                "                   ) then\r\n" + //
                "                      4\r\n" + //
                "                   when exists(\r\n" + //
                "                      select 1\r\n" + //
                "                        from starting_lots s2\r\n" + //
                "                       where length(s2.lot_num) > length(s.lot_num)\r\n" + //
                "                   ) then\r\n" + //
                "                      3\r\n" + //
                "                   else\r\n" + //
                "                      2\r\n" + //
                "                end\r\n" + //
                "          ) as sl_dr\r\n" + //
                "     from starting_lots s\r\n" + //
                ")\r\n" + //
                "--select * from starting_lots_ranked;\r\n" + //
                ",walk as (\r\n" + //
                "   select /*+ MATERIALIZE */ unique w.*,\r\n" + //
                "                 ppi.part_type as parent_part_type\r\n" + //
                "     from (\r\n" + //
                "      select lot_num,\r\n" + //
                "             lot_class,\r\n" + //
                "             part_id,\r\n" + //
                "             parent_lot_num,\r\n" + //
                "             parent_lot_class,\r\n" + //
                "             parent_part_id,\r\n" + //
                "             transdate,\r\n" + //
                "             transtime,\r\n" + //
                "             trans_dt,\r\n" + //
                "             parent_trans_dt,\r\n" + //
                "             level as lvl,\r\n" + //
                "             dense_rank()\r\n" + //
                "             over(partition by lot_num,\r\n" + //
                "                               part_id\r\n" + //
                "                  order by to_date(substr(\r\n" + //
                "                    prior trans_dt,\r\n" + //
                "                    1,\r\n" + //
                "                    18\r\n" + //
                "                 ),\r\n" + //
                "                'YYYY-MM-DD HH24:MI:SS') - to_date(substr(\r\n" + //
                "                    parent_trans_dt,\r\n" + //
                "                    1,\r\n" + //
                "                    18\r\n" + //
                "                 ),\r\n" + //
                "                'YYYY-MM-DD HH24:MI:SS')\r\n" + //
                "             ) as rnk\r\n" + //
                "        from (\r\n" + //
                "         select v.lot_num,\r\n" + //
                "                v.lot_class,\r\n" + //
                "                v.part_id,\r\n" + //
                "                v.parent_lot_num as lotg_parent_lot_num,\r\n" + //
                "                coalesce(\r\n" + //
                "                   t.originator,\r\n" + //
                "                   v.parent_lot_num\r\n" + //
                "                ) as parent_lot_num,\r\n" + //
                "                v.parent_lot_class,\r\n" + //
                "                v.parent_part_id,\r\n" + //
                "                trans_dt,\r\n" + //
                "                parent_trans_dt,\r\n" + //
                "                transdate,\r\n" + //
                "                transtime\r\n" + //
                "           from src_tgt_xref_with v\r\n" + //
                "           left join lotg_owner.orn_out_oracle_trak t\r\n" + //
                "         on v.parent_lot_num = t.lot_id\r\n" + //
                "            and v.parent_part_id = t.target_item\r\n" + //
                "            and t.target_item not like '%-PBU'\r\n" + //
                "           left join lotg_owner.orn_receipts r\r\n" + //
                "         on t.originator = r.lot_num\r\n" + //
                "            and t.target_item = r.part\r\n" + //
                "-- Exclude parent lots from the genealogy walk if they are parents in a merge transaction\r\n" + //
                "          where not exists (\r\n" + //
                "            select 1\r\n" + //
                "              from src_tgt_xref_with vx\r\n" + //
                "             where vx.lot_num = v.lot_num\r\n" + //
                "               and vx.parent_lot_num != v.parent_lot_num\r\n" + //
                "               and vx.transdate = v.transdate\r\n" + //
                "               and vx.transtime = v.transtime\r\n" + //
                "               and vx.part_id = v.part_id\r\n" + //
                "               and vx.parent_part_id = v.parent_part_id\r\n" + //
                "         )\r\n" + //
                "      ) v\r\n" + //
                "      connect by nocycle(prior parent_part_id = part_id\r\n" + //
                "      or regexp_substr(\r\n" + //
                "         prior parent_part_id,\r\n" + //
                "         '[^-]+[-]*[^-]+',\r\n" + //
                "         1\r\n" + //
                "      ) = regexp_substr(\r\n" + //
                "         part_id,\r\n" + //
                "         '[^-]+[-]*[^-]+',\r\n" + //
                "         1\r\n" + //
                "      ))\r\n" + //
                "         and prior parent_lot_num = lot_num\r\n" + //
                "         and prior parent_trans_dt >= trans_dt\r\n" + //
                "         and not ( lot_num = parent_lot_num\r\n" + //
                "         and part_id = parent_part_id\r\n" + //
                "         and lot_class = parent_lot_class ) start with exists (\r\n" + //
                "         select 1\r\n" + //
                "           from starting_lots_ranked sl\r\n" + //
                "          where sl.lot_num = v.lot_num\r\n" + //
                "            and sl.parent_lot_num = v.lotg_parent_lot_num\r\n" + //
                "            and sl.transdate = v.transdate\r\n" + //
                "            and sl.transtime = v.transtime\r\n" + //
                "            and sl.sl_dr = 1\r\n" + //
                "      )\r\n" + //
                "   ) w\r\n" + //
                "     left join lotg_owner.pc_item ppi\r\n" + //
                "   on w.parent_part_id = ppi.part_id\r\n" + //
                "    where lvl = 1\r\n" + //
                "       or ( ( ppi.part_type not in ( 'Substrate Part',\r\n" + //
                "                                     'Ingot Part',\r\n" + //
                "                                     'PCB Substrate Part',\r\n" + //
                "                                     'Package Substrate' )\r\n" + //
                "      and w.parent_part_id not like '%-BAS' ) )\r\n" + //
                ")\r\n" + //
                "--select * from walk order by LVL, trans_dt;\r\n" + //
                ",translate as (\r\n" + //
                "   select unique w.lot_num as lot,\r\n" + //
                "                 w.lot_class,\r\n" + //
                "                 w.lot_class as lot_owner,\r\n" + //
                "                 w.part_id as product,\r\n" + //
                "                 coalesce(\r\n" + //
                "                    cbt.type,\r\n" + //
                "                    'UNK'\r\n" + //
                "                 ) as part_type,\r\n" + //
                "                 case\r\n" + //
                "                    when w.parent_part_type in ( 'Substrate Part',\r\n" + //
                "                                                 'Ingot Part',\r\n" + //
                "                                                 'PCB Substrate Part',\r\n" + //
                "                                                 'Package Substrate' ) then\r\n" + //
                "                       lot_num\r\n" + //
                "                    else\r\n" + //
                "                       parent_lot_num\r\n" + //
                "                 end as parent_lot,\r\n" + //
                "                 w.parent_lot_class,\r\n" + //
                "                 case\r\n" + //
                "                    when w.parent_part_type in ( 'Substrate Part',\r\n" + //
                "                                                 'Ingot Part',\r\n" + //
                "                                                 'PCB Substrate Part',\r\n" + //
                "                                                 'Package Substrate' ) then\r\n" + //
                "                       part_id\r\n" + //
                "                    else\r\n" + //
                "                       parent_part_id\r\n" + //
                "                 end as parent_product,\r\n" + //
                "                 w.parent_part_type,\r\n" + //
                "                 case\r\n" + //
                "                    when w.parent_part_type in ( 'Substrate Part',\r\n" + //
                "                                                 'Ingot Part',\r\n" + //
                "                                                 'PCB Substrate Part',\r\n" + //
                "                                                 'Package Substrate' ) then\r\n" + //
                "                       'CHILD'\r\n" + //
                "                    else\r\n" + //
                "                       'PARENT'\r\n" + //
                "                 end as relationship,\r\n" + //
                "                 trans_dt,\r\n" + //
                "                 parent_trans_dt\r\n" + //
                "     from walk w\r\n" + //
                "     left join lotg_owner.lotg_bom_type cbt\r\n" + //
                "   on w.part_id = cbt.part\r\n" + //
                ")\r\n" + //
                "--select * from translate;\r\n" + //
                ",src_lot_walk as (\r\n" + //
                "   select lot,\r\n" + //
                "          product,\r\n" + //
                "          parent_lot,\r\n" + //
                "          parent_product,\r\n" + //
                "          relationship\r\n" + //
                "/* Uncomment below line to prefix lot class to source lot for die-level products\r\n" + //
                "--, CASE WHEN PARENT_PART_TYPE in ('DIE','RS') then PARENT_LOT_CLASS ELSE '' END AS PARENT_LOT_CLASS\r\n" + //
                "-- Comment below line if line above is uncommented*/,\r\n" + //
                "          '' as parent_lot_class,\r\n" + //
                "          connect_by_root lot as top,\r\n" + //
                "          rank()\r\n" + //
                "          over(partition by connect_by_root lot\r\n" + //
                "               order by trans_dt\r\n" + //
                "          ) as dr,\r\n" + //
                "          regexp_substr(\r\n" + //
                "             prior parent_product,\r\n" + //
                "             '[^-]+[-]*[^-]+',\r\n" + //
                "             1\r\n" + //
                "          ) as x1,\r\n" + //
                "          regexp_substr(\r\n" + //
                "             product,\r\n" + //
                "             '[^-]+[-]*[^-]+',\r\n" + //
                "             1\r\n" + //
                "          ) as x2\r\n" + //
                "     from translate w\r\n" + //
                "   connect by nocycle ( prior parent_product = product\r\n" + //
                "      and prior parent_lot = lot\r\n" + //
                "      and not ( product = parent_product\r\n" + //
                "      and lot = parent_lot )\r\n" + //
                "      and prior parent_trans_dt >= trans_dt )\r\n" + //
                "                --OR (regexp_substr(PRIOR PARENT_PRODUCT, '[^-]+[-]*[^-]+', 1) = regexp_substr(PRODUCT, '[^-]+[-]*[^-]+', 1) AND PRIOR PARENT_LOT = LOT AND PRIOR PARENT_TRANS_DT >= TRANS_DT))\r\n" + //
                "/* Added FG to accommodate splits in assembly or final test */ start with part_type in ( 'FG',\r\n" + //
                "                                                                        'WFR',\r\n" + //
                "                                                                        'WAFER',\r\n" + //
                "                                                                        'DIE',\r\n" + //
                "                                                                        'RS',\r\n" + //
                "                                                                        'UNK' )\r\n" + //
                ")\r\n" + //
                "--select * from src_lot_walk;\r\n" + //
                ",src_lot as (\r\n" + //
                "   select unique top as lot,\r\n" + //
                "                 parent_lot_class || parent_lot as source_lot,\r\n" + //
                "                 parent_product as source_product,\r\n" + //
                "                 relationship\r\n" + //
                "     from src_lot_walk w\r\n" + //
                "    where dr = 1\r\n" + //
                ")\r\n" + //
                "--select * from src_lot;\r\n" + //
                ",bom_site as (\r\n" + //
                "   select x.*,\r\n" + //
                "          dense_rank()\r\n" + //
                "          over(partition by start_part\r\n" + //
                "               order by rnk,\r\n" + //
                "                        lvl desc,\r\n" + //
                "                        site_eff_dt desc\r\n" + //
                "          ) as bom_rnk\r\n" + //
                "     from (\r\n" + //
                "      select pba.component_part_id,\r\n" + //
                "             coalesce(\r\n" + //
                "                pisa.site_id,\r\n" + //
                "                pba.site_id\r\n" + //
                "             )\r\n" + //
                "             ||\r\n" + //
                "             case\r\n" + //
                "                when coalesce(\r\n" + //
                "                      pisa.site_id,\r\n" + //
                "                      pba.site_id\r\n" + //
                "                   ) is null then\r\n" + //
                "                      ''\r\n" + //
                "                else\r\n" + //
                "                   ':'\r\n" + //
                "             end\r\n" + //
                "             ||\r\n" + //
                "             case\r\n" + //
                "                   when coalesce(\r\n" + //
                "                      pisa.site_id,\r\n" + //
                "                      pba.site_id\r\n" + //
                "                   ) = 'BE2' then\r\n" + //
                "                      'BELGAN FE (ARB)' /* PC_ITEMSITE sometimes has wrong description for BE2 with older parts */\r\n" + //
                "                   when pisa.site_desc is null then\r\n" + //
                "                      (\r\n" + //
                "                         select min(site_desc)\r\n" + //
                "                           from lotg_owner.pc_itemsite pis2\r\n" + //
                "                          where pba.site_id = pis2.site_id\r\n" + //
                "                      )\r\n" + //
                "                   else\r\n" + //
                "                      pisa.site_desc\r\n" + //
                "             end\r\n" + //
                "             as site_desc,\r\n" + //
                "             connect_by_root pba.part_id as start_part,\r\n" + //
                "             level as lvl,\r\n" + //
                "             pisa.site_eff_dt,\r\n" + //
                "             rank()\r\n" + //
                "             over(partition by pba.component_part_id\r\n" + //
                "                  order by pba.preference_cd,\r\n" + //
                "                           pba.alternate_bill,\r\n" + //
                "                           pisa.site_desc desc\r\n" + //
                "             ) as rnk\r\n" + //
                "        from lotg_owner.pc_bom pba\r\n" + //
                "        left join lotg_owner.pc_item comp\r\n" + //
                "      on pba.part_id = comp.part_id\r\n" + //
                "        left join lotg_owner.pc_itemsite pisa\r\n" + //
                "      on pba.part_id = pisa.part_id\r\n" + //
                "         and pba.site_id = pisa.site_id\r\n" + //
                "      connect by nocycle prior component_part_id = pba.part_id\r\n" + //
                "         and comp.part_type not in ( 'Substrate Part',\r\n" + //
                "                                     'Ingot Part',\r\n" + //
                "                                     'PCB Substrate Part',\r\n" + //
                "                                     'Package Substrate' ) start with pba.part_id in (\r\n" + //
                "         select distinct source_product\r\n" + //
                "           from src_lot\r\n" + //
                "      )\r\n" + //
                "   ) x\r\n" + //
                "    where x.rnk = 1\r\n" + //
                ")\r\n" + //
                "--select * from bom_site where bom_rnk = 1;\r\n" + //
                ",fab_info as (\r\n" + //
                "   select x.lot_num as lot,\r\n" + //
                "          x.bank_code,\r\n" + //
                "          x.rnk,\r\n" + //
                "          ornr.vendor_name ornr_v_name,\r\n" + //
                "          mbs.mfg_area_desc as mbs_mfg_desc,\r\n" + //
                "          ornr.mfg_area_cd as ornr_mfg_area_cd,\r\n" + //
                "          mbs.mfg_area_cd as mbs_mfg_area_cd,\r\n" + //
                "          case\r\n" + //
                "             when coalesce(\r\n" + //
                "                ornr.vendor_name,\r\n" + //
                "                mbs.mfg_area_desc\r\n" + //
                "             ) like '%NON%RECORDING%BANK'\r\n" + //
                "                 or ( mbs.mfg_area_cd is null\r\n" + //
                "                and ornr.mfg_area_cd is null )\r\n" + //
                "                 or ( mbs.mfg_area_cd is not null\r\n" + //
                "                and mbs.mfg_stage_cd not in ( 'FAB',\r\n" + //
                "                                              'ADJ',\r\n" + //
                "                                              'DCY',\r\n" + //
                "                                              'PP' )\r\n" + //
                "                and exists (\r\n" + //
                "                select unique site_desc\r\n" + //
                "                  from bom_site bs\r\n" + //
                "                 where bs.bom_rnk = 1\r\n" + //
                "                   and bs.start_part = x.source_product\r\n" + //
                "                   and rownum = 1\r\n" + //
                "             ) ) /*If the area code for this stage isn't fab, look it up in the BOM */ then\r\n" + //
                "                coalesce(\r\n" + //
                "                   (\r\n" + //
                "                      select unique site_desc\r\n" + //
                "                        from bom_site bs\r\n" + //
                "                       where bs.bom_rnk = 1\r\n" + //
                "                         and bs.start_part = x.source_product\r\n" + //
                "                         and rownum = 1\r\n" + //
                "                   ),\r\n" + //
                "                   'UNKNOWN'\r\n" + //
                "                )\r\n" + //
                "             when coalesce(\r\n" + //
                "                ornr.mfg_area_cd,\r\n" + //
                "                mbs.mfg_area_cd\r\n" + //
                "             ) is not null then\r\n" + //
                "                coalesce(\r\n" + //
                "                   ornr.mfg_area_cd,\r\n" + //
                "                   mbs.mfg_area_cd\r\n" + //
                "                )\r\n" + //
                "                || ':'\r\n" + //
                "                || coalesce(\r\n" + //
                "                   ornr.vendor_name,\r\n" + //
                "                   mbs.mfg_area_desc\r\n" + //
                "                )\r\n" + //
                "             else\r\n" + //
                "                'UNKNOWN'\r\n" + //
                "          end as fab_name,\r\n" + //
                "          coalesce(\r\n" + //
                "             mbs.mfg_stage_desc,\r\n" + //
                "             'RECEIPT'\r\n" + //
                "          ) as fab_stage,\r\n" + //
                "          x.source_product\r\n" + //
                "     from (\r\n" + //
                "      select sl.source_lot as lot_num,\r\n" + //
                "             parent_lot_class,\r\n" + //
                "             transdate,\r\n" + //
                "             transtime,\r\n" + //
                "             t.transaction_dt,\r\n" + //
                "             case\r\n" + //
                "                when sl.relationship = 'CHILD' then\r\n" + //
                "                   b.from_bank_code\r\n" + //
                "                else\r\n" + //
                "                   b.to_bank_code\r\n" + //
                "             end as bank_code,\r\n" + //
                "             source_product,\r\n" + //
                "             rank()\r\n" + //
                "             over(partition by sl.source_lot\r\n" + //
                "                  order by transdate,\r\n" + //
                "                           transtime,\r\n" + //
                "                           t.transaction_dt\r\n" + //
                "             ) as rnk\r\n" + //
                "        from src_lot sl\r\n" + //
                "        left join src_tgt_xref_with b\r\n" + //
                "      on sl.source_lot = b.lot_num\r\n" + //
                "         and sl.source_product = b.part_id\r\n" + //
                "--      LEFT JOIN src_tgt_xref_with b on ((sl.RELATIONSHIP = 'CHILD' AND sl.SOURCE_LOT = b.LOT_NUM and sl.SOURCE_PRODUCT = b.PART_ID) \r\n" + //
                "--                                      or (sl.RELATIONSHIP = 'PARENT' AND sl.SOURCE_LOT = b.PARENT_LOT_NUM and sl.SOURCE_PRODUCT = b.PARENT_PART_ID ))\r\n" + //
                "        left join lotg_owner.orn_out_oracle_trak t\r\n" + //
                "      on sl.source_lot = t.originator\r\n" + //
                "        join lotg_owner.pc_item i\r\n" + //
                "      on sl.source_product = i.part_id\r\n" + //
                "       where i.part_type not in ( 'Substrate Part',\r\n" + //
                "                                  'Ingot Part',\r\n" + //
                "                                  'PCB Substrate Part',\r\n" + //
                "                                  'Package Substrate' )\r\n" + //
                "   ) x\r\n" + //
                "     left join lotg_owner.mfg_bank_to_stage mbs\r\n" + //
                "   on x.bank_code = mbs.bank_cd\r\n" + //
                "     left join lotg_owner.orn_receipts ornr\r\n" + //
                "   on x.lot_num = ornr.lot_num\r\n" + //
                "      and x.source_product = ornr.part\r\n" + //
                "    where x.rnk = 1\r\n" + //
                ")\r\n" + //
                "--select * from fab_info;\r\n" + //
                "select *\r\n" + //
                "  from (\r\n" + //
                "   select unique t.lot,\r\n" + //
                "                 t.parent_lot,\r\n" + //
                "                 case\r\n" + //
                "                    when regexp_like ( t.product,\r\n" + //
                "                                       '^[^-]+-[^-]+-[^-]+-[^-]+-[^-][^-][^-]$' ) then\r\n" + //
                "                       regexp_substr(\r\n" + //
                "                          t.product,\r\n" + //
                "                          '^[^-]+-[^-]+-[^-]+-[^-]+'\r\n" + //
                "                       )\r\n" + //
                "                    when regexp_like ( t.product,\r\n" + //
                "                                       '^[^-]+-[^-]+-[^-][^-][^-]$' ) then\r\n" + //
                "                       regexp_substr(\r\n" + //
                "                          t.product,\r\n" + //
                "                          '^[^-]+-[^-]+'\r\n" + //
                "                       )\r\n" + //
                "                    when regexp_like ( t.product,\r\n" + //
                "                                       '^[^-]+-[^-][^-][^-]$' ) then\r\n" + //
                "                       regexp_substr(\r\n" + //
                "                          t.product,\r\n" + //
                "                          '^[^-]+'\r\n" + //
                "                       )\r\n" + //
                "                    else\r\n" + //
                "                       t.product\r\n" + //
                "                 end as product,\r\n" + //
                "                 'NOT AVAILABLE' as lot_owner,\r\n" + //
                "                 case\r\n" + //
                "                    when regexp_like ( t.parent_product,\r\n" + //
                "                                       '^[^-]+-[^-]+-[^-]+-[^-]+-[^-][^-][^-]$' ) then\r\n" + //
                "                       regexp_substr(\r\n" + //
                "                          t.parent_product,\r\n" + //
                "                          '^[^-]+-[^-]+-[^-]+-[^-]+'\r\n" + //
                "                       )\r\n" + //
                "                    when regexp_like ( t.parent_product,\r\n" + //
                "                                       '^[^-]+-[^-]+-[^-][^-][^-]$' ) then\r\n" + //
                "                       regexp_substr(\r\n" + //
                "                          t.parent_product,\r\n" + //
                "                          '^[^-]+-[^-]+'\r\n" + //
                "                       )\r\n" + //
                "                    when regexp_like ( t.parent_product,\r\n" + //
                "                                       '^[^-]+-[^-][^-][^-]$' ) then\r\n" + //
                "                       regexp_substr(\r\n" + //
                "                          t.parent_product,\r\n" + //
                "                          '^[^-]+'\r\n" + //
                "                       )\r\n" + //
                "                    else\r\n" + //
                "                       t.parent_product\r\n" + //
                "                 end as parent_product,\r\n" + //
                "                 case\r\n" + //
                "                    when f.fab_name like 'UV5:%' then\r\n" + //
                "                       regexp_replace(\r\n" + //
                "                          case\r\n" + //
                "                             when sl.source_lot is not null then\r\n" + //
                "                                sl.source_lot\r\n" + //
                "                             else t.parent_lot\r\n" + //
                "                          end,\r\n" + //
                "                          '\\.0\\d\\d$',\r\n" + //
                "                          '',\r\n" + //
                "                          1,\r\n" + //
                "                          1\r\n" + //
                "                       )\r\n" + //
                "                    when f.fab_name like 'USR:%' then\r\n" + //
                "                       substr(\r\n" + //
                "                          case\r\n" + //
                "                             when sl.source_lot is not null then\r\n" + //
                "                                sl.source_lot\r\n" + //
                "                             else t.parent_lot\r\n" + //
                "                          end,\r\n" + //
                "                          1,\r\n" + //
                "                          8\r\n" + //
                "                       )\r\n" + //
                "                    when f.fab_name like 'USU:%' then\r\n" + //
                "                       substr(\r\n" + //
                "                          case\r\n" + //
                "                             when sl.source_lot is not null then\r\n" + //
                "                                sl.source_lot\r\n" + //
                "                             else t.parent_lot\r\n" + //
                "                          end,\r\n" + //
                "                          1,\r\n" + //
                "                          6\r\n" + //
                "                       )\r\n" + //
                "                    when ( f.fab_name like 'JND:%'\r\n" + //
                "                        or f.fab_name like 'MY2:%' )\r\n" + //
                "                       and regexp_like ( sl.source_lot,\r\n" + //
                "                                         '^.+0\\d$' ) then\r\n" + //
                "                       substr(\r\n" + //
                "                          case\r\n" + //
                "                             when sl.source_lot is not null then\r\n" + //
                "                                sl.source_lot\r\n" + //
                "                             else t.parent_lot\r\n" + //
                "                          end,\r\n" + //
                "                          1,\r\n" + //
                "                          8\r\n" + //
                "                       )\r\n" + //
                "                    when ( f.fab_name like 'MYD:%'\r\n" + //
                "                        or f.fab_name like 'ISMFAB:%' )\r\n" + //
                "                       and regexp_like (\r\n" + //
                "                       case\r\n" + //
                "                          when sl.source_lot is not null then\r\n" + //
                "                             sl.source_lot\r\n" + //
                "                          else\r\n" + //
                "                             t.parent_lot\r\n" + //
                "                       end,\r\n" + //
                "                       '^.+\\.[0-9]+[A-Z]$' ) then\r\n" + //
                "                       substr(\r\n" + //
                "                          case\r\n" + //
                "                             when sl.source_lot is not null then\r\n" + //
                "                                sl.source_lot\r\n" + //
                "                             else t.parent_lot\r\n" + //
                "                          end,\r\n" + //
                "                          1,\r\n" + //
                "                          instr(\r\n" + //
                "                             case\r\n" + //
                "                                when sl.source_lot is not null then\r\n" + //
                "                                   sl.source_lot\r\n" + //
                "                                else t.parent_lot\r\n" + //
                "                             end,\r\n" + //
                "                             '.'\r\n" + //
                "                          ) - 1\r\n" + //
                "                       )\r\n" + //
                "                    when f.fab_name like 'CZ4:%'\r\n" + //
                "                        or f.fab_name like 'ISMFAB:%'\r\n" + //
                "                        or f.fab_name like 'MY2:%'\r\n" + //
                "                        or f.fab_name like 'UVA:%'\r\n" + //
                "                        or f.fab_name like 'LFOUNDRY:%' then\r\n" + //
                "                       substr(\r\n" + //
                "                          case\r\n" + //
                "                             when sl.source_lot is not null then\r\n" + //
                "                                sl.source_lot\r\n" + //
                "                             else t.parent_lot\r\n" + //
                "                          end,\r\n" + //
                "                          1,\r\n" + //
                "                          7\r\n" + //
                "                       )\r\n" + //
                "                    when ( f.fab_name like 'UMC:%'\r\n" + //
                "                        or f.fab_name like 'MYD:%' )\r\n" + //
                "                       and regexp_like (\r\n" + //
                "                       case\r\n" + //
                "                          when sl.source_lot is not null then\r\n" + //
                "                             sl.source_lot\r\n" + //
                "                          else\r\n" + //
                "                             t.parent_lot\r\n" + //
                "                       end,\r\n" + //
                "                       '^.+\\.[0-9]+$' ) then\r\n" + //
                "                       substr(\r\n" + //
                "                          case\r\n" + //
                "                             when sl.source_lot is not null then\r\n" + //
                "                                sl.source_lot\r\n" + //
                "                             else t.parent_lot\r\n" + //
                "                          end,\r\n" + //
                "                          1,\r\n" + //
                "                          instr(\r\n" + //
                "                             case\r\n" + //
                "                                when sl.source_lot is not null then\r\n" + //
                "                                   sl.source_lot\r\n" + //
                "                                else t.parent_lot\r\n" + //
                "                             end,\r\n" + //
                "                             '.'\r\n" + //
                "                          ) - 1\r\n" + //
                "                       )\r\n" + //
                "                    when sl.source_lot is not null then\r\n" + //
                "                       sl.source_lot\r\n" + //
                "                    else\r\n" + //
                "                       t.parent_lot\r\n" + //
                "                 end as source_lot,\r\n" + //
                "                 case\r\n" + //
                "                    when sl.source_lot is not null then\r\n" + //
                "                       sl.source_product\r\n" + //
                "                    else\r\n" + //
                "                       t.parent_product\r\n" + //
                "                 end as \"WAFER_PART/ALTERNATE_PRODUCT\",\r\n" + //
                "                 f.fab_name as fab,\r\n" + //
                "                 'NOT AVAILABLE' as lot_type,\r\n" + //
                "                 t.lot_class,\r\n" + //
                "                 'NOT AVAILABLE' as maskset  /* Need to get from Data Warehouse by product */,\r\n" + //
                "                 case\r\n" + //
                "                    when sl.source_lot is not null then\r\n" + //
                "                       sl.source_product\r\n" + //
                "                    else\r\n" + //
                "                       t.parent_product\r\n" + //
                "                 end as \"PRODUCT_CODE\",\r\n" + //
                "                 dense_rank()\r\n" + //
                "                 over(partition by t.lot\r\n" + //
                "                      order by parent_trans_dt,\r\n" + //
                "                               trans_dt\r\n" + //
                "                 ) as dr\r\n" + //
                "     from translate t\r\n" + //
                "     left join src_lot sl\r\n" + //
                "   on t.parent_lot = sl.lot\r\n" + //
                "     left join fab_info f\r\n" + //
                "   on case\r\n" + //
                "         when sl.source_lot is not null then\r\n" + //
                "            sl.source_lot\r\n" + //
                "         else\r\n" + //
                "            t.parent_lot\r\n" + //
                "      end = f.lot\r\n" + //
                "/*Filter on lot here.  Need four lots: \r\n" + //
                "1. Original lot (no changes)\r\n" + //
                "2. Remove dot (.) if it exists\r\n" + //
                "3. Replace dot (.) with zero (0) \r\n" + //
                "4. *IF* lot ends with .\\d+[A-Z], remove dot and remove last character (e.g., RM12016.1F -> RM120161, RM12016.12Q -> RM1201612)\r\n" + //
                ":lotId, :lotIdWithoutDot, :lotIdWith0, substr(:lotIdWith0, 3, 50), :lotIdWithoutDotAndLettersInSuffix\r\n" + //
                "*/\r\n" + //
                "/*WHERE (t.LOT in ('$lot1', '$lot2', '$lot3', substr('$lot3', 3, 50), '$lot4'))*/\r\n" + //
                "    where ( t.lot in ( :lotId,\r\n" + //
                "                       :lotIdWithoutDot,\r\n" + //
                "                       :lotIdWith0,\r\n" + //
                "                       substr(\r\n" + //
                "                       :lotIdWith0,\r\n" + //
                "                       3,\r\n" + //
                "                       50\r\n" + //
                "                    ),\r\n" + //
                "                       :lotIdWithoutDotAndLettersInSuffix ) )\r\n" + //
                ")\r\n" + //
                "/* Always return the result with the oldest datetime to ensure the correct product is returned */\r\n" + //
                " where dr = 1";

    private static final String LOT = "LOT";
    private static final String PRODUCT = "PRODUCT";
    private static final String SOURCE_LOT = "SOURCE_LOT";
    private static final String WAFER_PART_ALTERNATE_PRODUCT = "WAFER_PART/ALTERNATE_PRODUCT";
    private static final String FAB = "FAB";
    private static final String LOT_TYPE = "LOT_TYPE";
    private static final String LOT_CLASS = "LOT_CLASS";
    private static final String MASK_SET = "MASKSET";
    private static final String PRODUCT_CODE = "PRODUCT_CODE";

    private final EntityManager entityManager;

    public LotgInfoCustomRepository(
            @Qualifier("lotGEntityManager") EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public LotGInfo findLotGInfo(String lot) {
        try {
            final StringBuilder lotWithoutDot = new StringBuilder();
            final StringBuilder lotWith0 = new StringBuilder();
            final StringBuilder lotWithoutDotAndLettersInSuffix = new StringBuilder();

            if (lot.contains(".")) {
                final int dotInLot = lot.lastIndexOf(".");
                lotWithoutDot.append(lot, 0, dotInLot);
                lotWithoutDot.append(lot.substring(dotInLot + 1));

                lotWith0.append(lot, 0, dotInLot);
                lotWith0.append("0");
                lotWith0.append(lot.substring(dotInLot + 1));

                lotWithoutDotAndLettersInSuffix.append(lot, 0, dotInLot);
                final String lotSuffixAfterDot = lot.substring(dotInLot + 1);
                final String lotSuffixAfterDotWithoutLetters = lotSuffixAfterDot.replaceAll("([A-Za-z])", "");
                lotWithoutDotAndLettersInSuffix.append(lotSuffixAfterDotWithoutLetters);

            } else {
                lotWithoutDot.append(lot);
                lotWith0.append(lot);
                lotWithoutDotAndLettersInSuffix.append(lot);
            }

            Query query = entityManager.createNativeQuery(SQL_LOTG_QUERY, Tuple.class);

            query.setParameter("lotId", lot);
            LOG.info("1. Orig lotId='{}'", lot);
            query.setParameter("lotIdWithoutDot", lotWithoutDot.toString());
            LOG.info("2. W/O dot lotId='{}'", lotWithoutDot);
            query.setParameter("lotIdWith0", lotWith0.toString());
            LOG.info("3. With zero lotId='{}'", lotWith0);
            query.setParameter("lotIdWithoutDotAndLettersInSuffix", lotWithoutDotAndLettersInSuffix.toString());
            LOG.info("4.lotIdWithoutDotAndLettersInSuffix lotId='{}'", lotWithoutDotAndLettersInSuffix);
            return convertResultToLotGInfo((Tuple) query.getSingleResult());
        } catch (NoResultException nre) {

            return null;
        }
    }

    private LotGInfo convertResultToLotGInfo(Tuple tuple) {
        LotGInfo lotGInfo = new LotGInfo();
        if (tuple != null && tuple.getElements() != null) {
            lotGInfo.setStatus(Status.FOUND);
            lotGInfo.setLot((String) tuple.get(LOT));
            lotGInfo.setProduct((String) tuple.get(PRODUCT));
            lotGInfo.setSourceLot((String) tuple.get(SOURCE_LOT));
            lotGInfo.setWaferPartAlternateProduct((String) tuple.get(WAFER_PART_ALTERNATE_PRODUCT));
            lotGInfo.setFab((String) tuple.get(FAB));
            lotGInfo.setLotType((String) tuple.get(LOT_TYPE));
            lotGInfo.setLotClass((String) tuple.get(LOT_CLASS));
            lotGInfo.setMaskSet((String) tuple.get(MASK_SET));
            lotGInfo.setProductionCode((String) tuple.get(PRODUCT_CODE));
        } else {
            lotGInfo.setStatus(Status.NO_DATA);
        }

        return lotGInfo;
    }
}