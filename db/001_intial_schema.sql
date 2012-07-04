-- TABLES FOR: payroll_management_system
-- brokers
-- broker_joint_entities
-- broker_override_entities
-- broker_payout_structures
-- broker_payroll_fees
-- broker_payroll_override_submissions
-- broker_payroll_payout_submissions
-- broker_payroll_submissions
-- broker_sales_assistants
-- commission_months
-- employees
-- expenses
-- holiday_dates
-- report_fbnr074p
-- sales_assistants
-- sales_assistant_payroll_data_submissions
-- special_payroll_items
-- static_special_payroll_item_categories
-- static_expense_categories
-- telemarketers
-- trade_revenue_trade_date_trailer
-- trade_revenue_trade_date

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


DROP TABLE IF EXISTS `brokers`;
CREATE TABLE `brokers` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `active` tinyint(1) unsigned NOT NULL,
    `broker_id` char(3) NOT NULL,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `created_at` date NOT NULL,
    `created_by` int(11) unsigned NOT NULL,
    `updated_at` date NOT NULL,
    `updated_by` int(11) unsigned NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `broker_joint_entities`;
CREATE TABLE `broker_joint_entities` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `active` tinyint(1) unsigned NOT NULL,
    `associated_broker_id` char(3) DEFAULT NULL, -- FIELDS FROM repNums: mainRep
    `joint_broker_id` char(3) DEFAULT NULL, -- FIELDS FROM repNums: altRep
    `note` varchar(50) NOT NULL, -- FIELDS FROM repNums: misc
    `percentage` double NOT NULL, -- FIELDS FROM repNums: splitPercent
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `broker_override_entities`;
CREATE TABLE `broker_override_entities` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `active` tinyint(1) unsigned NOT NULL,
    `associated_broker_id` char(3) DEFAULT NULL, -- FIELD FROM repNums: altRep
    `note` varchar(50) NOT NULL, -- FIELD FROM repNums: misc
    `override_broker_id` char(3) DEFAULT NULL, -- FIELD FROM repNums: mainRep
    `percentage` double NOT NULL, -- FIELD FROM repNums: splitPercent
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `broker_payout_structures`;
CREATE TABLE `broker_payout_structures` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `broker_id` char(3) DEFAULT NULL, -- FIELD FROM payoutStructure: repNum
    `commission_max` double NOT NULL, -- FIELD FROM payoutStructure: maxAmt
    `commission_min` double NOT NULL, -- FIELD FROM payoutStructure: minAmt
    `note` varchar(50) NOT NULL, -- FIELD FROM payoutStructure: misc
    `percentage` double NOT NULL, -- FIELD FROM payoutStructure: payPercent
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `broker_payroll_fees`;
CREATE TABLE `broker_payroll_fees` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `amount` double NOT NULL, -- FIELD FROM eamFees: feeAmount --
    `description` varchar(255) NOT NULL, -- FIELD FROM eamFees: misc --
    `label` varchar(50) NOT NULL, -- FIELD FROM eamFees: feeName --
    `modifier` char(1) NOT NULL,
    `name` varchar(50) NOT NULL,
    `type` char(1) NOT NULL, -- FIELD FROM eamFees: modifier --
    `created_at` datetime NOT NULL,
    `created_by` int(11) unsigned NOT NULL,
    `updated_at` datetime NOT NULL,
    `updated_by` int(11) unsigned NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `broker_payroll_override_submissions`;
CREATE TABLE `broker_payroll_override_submissions` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `amount` double NOT NULL,
    `broker_id` char(3) DEFAULT NULL, -- FIELD FROM monthEndPayoutOverride: repNum
    `commission_month` date NOT NULL, -- FIELD FROM monthEndPayoutOverride: monthEndDate
    `note` varchar(50) NOT NULL,
    `override_broker_id` varchar(50) NOT NULL, -- FIELD FROM monthEndPayoutOverride: misc
    `percentage` double NOT NULL, -- FIELD FROM monthEndPayoutOverride: percent
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `broker_payroll_payout_submissions`;
CREATE TABLE `broker_payroll_payout_submissions` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `amount` double NOT NULL,
    `broker_id` char(3) DEFAULT NULL, -- FIELD FROM monthEndPayoutOverride: repNum
    `commission_month` date NOT NULL, -- FIELD FROM monthEndPayoutOverride: monthEndDate
    `note` varchar(50) NOT NULL, -- FIELD FROM monthEndPayoutOverride: misc
    `percentage` double NOT NULL, -- FIELD FROM monthEndPayoutOverride: percent
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `broker_payroll_submissions`;
CREATE TABLE `broker_payroll_submissions` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `amount_payable` double NOT NULL,
    `broker_id` char(3) DEFAULT NULL,
    `commission_month` date NOT NULL,
    `gross_from_sales` double NOT NULL,
    `net_before_expenses` double NOT NULL,
    `net_from_sales` double NOT NULL,
    `note` varchar(50) NOT NULL,
    `total_adjusted_gross` double NOT NULL,
    `total_check` double NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `broker_sales_assistants`;
CREATE TABLE `broker_sales_assistants` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `broker_id` char(3) DEFAULT NULL, -- FIELD FROM salesAssistantData: repNum
    `note` varchar(50) NOT NULL, -- FIELD FROM salesAssistantData: misc
    `percentage` double NOT NULL, -- FIELD FROM salesAssistantData: percent
    `sales_assistant_id` char(3) DEFAULT NULL, -- FIELD FROM salesAssistantData: salesAssistantId
    `created_at` date NOT NULL,
    `created_by` int(11) unsigned NOT NULL,
    `updated_at` date NOT NULL,
    `updated_by` int(11) unsigned NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `commission_months`;
CREATE TABLE `commission_months` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `commission_month_start` date NOT NULL,
    `commission_month_end` date NOT NULL,
    `month_label` varchar(50) NOT NULL,
    `next_month` date NOT NULL,
    `previous_month` date NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `active` tinyint(1) unsigned NOT NULL,
    `employee_id` char(3) NOT NULL,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `created_at` date NOT NULL,
    `created_by` int(11) unsigned NOT NULL,
    `updated_at` date NOT NULL,
    `updated_by` int(11) unsigned NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `expenses`;
CREATE TABLE `expenses` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT, -- FIELD FROM miscExpenses: expId --
    `amount` double NOT NULL,
    `broker_id` int(11) unsigned NOT NULL, -- FIELD FROM miscExpenses: repNum --
    `commission_month` date NOT NULL, -- FIELD FROM miscExpenses: monthEndDate --
    `description` varchar(255) NOT NULL, -- FIELD FROM miscExpenses: misc --
    `label` varchar(50) NOT NULL, -- FIELD FROM miscExpenses: expense --
    `created_at` datetime NOT NULL,
    `created_by` int(11) unsigned NOT NULL,
    `updated_at` datetime NOT NULL,
    `updated_by` int(11) unsigned NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `holiday_dates`;
CREATE TABLE `holiday_dates` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `commission_month` date NOT NULL, -- FIELDS FROM holidayDates: commissionMonthEnd --
    `holiday_date` date NOT NULL, -- FIELDS FROM holidayDates: holidayDate --
    `label` varchar(50) NOT NULL, -- FIELDS FROM holidayDates: holidayName --
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- TESTED & VERIFIED report_fbnr074p --
DROP TABLE IF EXISTS  `report_fbnr074p`;
CREATE TABLE  `report_fbnr074p` (
    `id` INT( 11 ) UNSIGNED NOT NULL AUTO_INCREMENT,
    `amount_of_trades` INT( 11 ) UNSIGNED NOT NULL,
    `branch` CHAR( 3 ) NOT NULL,
    `broker_id` CHAR( 3 ) NOT NULL,
    `clearing` DOUBLE NOT NULL,
    `commission` DOUBLE NOT NULL,
    `commission_month` DATE NOT NULL,
    `concession` DOUBLE NOT NULL,
    `concession_away` DOUBLE NOT NULL,
    `execution` DOUBLE NOT NULL,
    `payout` DOUBLE NOT NULL,
    `created_at` DATETIME NOT NULL,
    `created_by` INT( 11 ) UNSIGNED NOT NULL,
PRIMARY KEY (  `id` )
) ENGINE = MYISAM DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `sales_assistants`;
CREATE TABLE `sales_assistants` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `active` tinyint(1) unsigned NOT NULL,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `sales_assistant_id` char(3) DEFAULT NULL,
    `created_at` datetime NOT NULL,
    `created_by` int(11) unsigned NOT NULL,
    `updated_at` datetime NOT NULL,
    `updated_by` int(11) unsigned NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sales_assistant_payroll_data_submissions`;
CREATE TABLE `sales_assistant_payroll_data_submissions` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `amount_payable` double NOT NULL,
    `commission_month` date NOT NULL,
    `gross_from_sales` double NOT NULL,
    `net_before_expenses` double NOT NULL,
    `net_from_sales` double NOT NULL,
    `note` varchar(50) NOT NULL,
    `sales_assistant_id` char(3) DEFAULT NULL,
    `total_adjusted_gross` double NOT NULL,
    `total_check` double NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `special_payroll_items`;
CREATE TABLE `special_payroll_items` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT, -- FIELD FROM dealAdvBal: dabId --
    `amount` double NOT NULL,
    `broker_id` char(3) NOT NULL, -- FIELD FROM dealAdvBal: repNum --
    `commission_month` date NOT NULL, -- FIELD FROM dealAdvBal: monthEndDate --
    `description` varchar(255) NOT NULL, -- FIELD FROM dealAdvBal: misc --
    `label` varchar(50) NOT NULL, -- FIELD FROM dealAdvBal: type --
    `modifier` char(1) NOT NULL, -- MAY NOT NEED THIS FIELD -- 
    `created_at` date NOT NULL,
    `created_by` int(11) unsigned NOT NULL,
    `updated_at` date NOT NULL,
    `updated_by` int(11) unsigned NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `static_special_payroll_item_categories`;
CREATE TABLE `static_special_payroll_item_categories` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `category` varchar(50) NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `static_expense_categories`;
CREATE TABLE `static_expense_categories` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `category` varchar(50) NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `telemarketers`;
CREATE TABLE `telemarketers` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `active` tinyint(1) unsigned NOT NULL,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `telemarketer_id` char(3) DEFAULT NULL,
    `created_at` datetime NOT NULL,
    `created_by` int(11) unsigned NOT NULL,
    `updated_at` datetime NOT NULL,
    `updated_by` int(11) unsigned NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `trade_revenue_trade_date_trailer`;
CREATE TABLE `trade_revenue_trade_date_trailer` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `clearing_charge` double NOT NULL,
    `commission` double NOT NULL,
    `concession` double NOT NULL,
    `logical_records` int(11) unsigned NOT NULL,
    `logical_records_ht` int(11) unsigned NOT NULL,
    `principal` double NOT NULL,
    `run_date` date NOT NULL,
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `trade_revenue_trade_date`;
CREATE TABLE `trade_revenue_trade_date` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `record_number_01` varchar(2),
	`firm_01` varchar(4),
	`buy_sell_code_01` varchar(1),
	`trade_date_01` varchar(8),
	`settlement_date_01` varchar(8),
	`market_code_01` varchar(1),
    `blotter_code_01` varchar(1),
    `cancel_code_01` varchar(1),
    `street_side_code_01` varchar(1),
    `due_bill_01` varchar(1),
    `correction_code_01` varchar(1),
    `branch_01_a` varchar(3),
    `account_number_01` varchar(6),
    `account_type_01` varchar(1),
    `country_code_01` varchar(2),
    `cusip_01` varchar(9),
    `filler_01_a` varchar(5),
    `basis_price_code_01` varchar(1),
    `run_date_01` varchar(8),
    `trade_reference_number_01` varchar(11),
    `user_reference_number_01` varchar(11),
    `canceled_combined_reference_01` varchar(11),
    `batch_01_a` varchar(4),
    `record_number_02` varchar(2),
    `batch_02_b` varchar(1),
    `count_02` varchar(6),
    `symbol_02` varchar(16),
    `security_type_02` varchar(1),
    `security_type_modifier_02` varchar(1),
    `security_type_calculation_02` varchar(1),
    `cns_code_02` varchar(1),
    `primary_exchange_02` varchar(2),
    `dtc_eligiblity_code_02` varchar(1),
    `foreign_code_02` varchar(1),
    `registered_rep_enter_rep_02` varchar(3),
    `state_country_code_02` varchar(3),
    `ny_tax_02` varchar(1),
    `securities_instructions_02` varchar(1),
    `service_02` varchar(1),
    `parent_account_02` varchar(9),
    `agency_code_02` varchar(8),
    `filler_02_b` varchar(1),
    `mode_del_02` varchar(1),
    `proceeds_instructions_02` varchar(1),
    `cash_dividend_instructions_02` varchar(1),
    `sales_prod_02` varchar(1),
    `trade_unit_02` varchar(1),
    `dif_principal_02` varchar(11),
    `short_name_02` varchar(10),
    `account_classification_02` varchar(2),
    `citizen_code_02` varchar(1),
    `country_of_tax_residency_02` varchar(3),
    `transfer_legend_code_02` varchar(1),
    `market_maker_code_02` varchar(1),
    `rr_penalty_02` varchar(1),
    `minor_executing_broker_02` varchar(4),
    `minor_clearing_broker_02_a` varchar(2),
    `record_number_03` varchar(2),
    `minor_clearing_broker_03_b` varchar(2),
    `offset_account_03` varchar(10),
    `offset_shortname_03` varchar(10),
    `offset_rr_03` varchar(3),
    `offset_commission_03` varchar(7),
    `conf_br_override_03` varchar(3),
    `source_03` varchar(1),
    `type_of_order_03` varchar(1),
    `confirmation_print_03` varchar(1),
    `comparison_print_03` varchar(1),
    `commission_accumulation_03` varchar(1),
    `commission_schedule_03` varchar(2),
    `blotter_override_code_03` varchar(1),
    `nscc_code_03` varchar(1),
    `tax_accumulation_03` varchar(1),
    `commission_concession_code_03_a` varchar(1),
    `substitute_blotter_03` varchar(1),
    `quantity_03` varchar(16),
    `price_03` varchar(18),
    `alphaprice_dollar_03` varchar(9),
    `alphaprice_space_03` varchar(1),
    `alphaprice_fraction_03_a` varchar(8),
    `record_number_04` varchar(2),
    `alphaprice_fraction_04_b` varchar(1),
    `plus_minus_04` varchar(18),
    `principal_04` varchar(15),
    `accrued_interest_04` varchar(12),
    `trade_commission_04` varchar(10),
    `state_tax_04` varchar(8),
    `sec_fee_04` varchar(8),
    `certificate_fee_04` varchar(8),
    `postage_04` varchar(8),
    `service_charge_misc_fee_04` varchar(10),
    `net_04_a` varchar(1),
    `record_number_05` varchar(2),
    `net_05_b` varchar(14),
    `brokerage_05` varchar(10),
    `trade_concession_05` varchar(10),
    `other_exchange_commission_05` varchar(10),
    `standard_commission_05` varchar(10),
    `limit_order_charge_05` varchar(8),
    `number_of_security_description_lines_05` varchar(1),
    `security_description_line_05_a` varchar(20),
    `security_description_line_05_b` varchar(16),
    `record_number_06` varchar(2),
    `security_description_line_06_c` varchar(4),
    `security_description_line_06_d` varchar(20),
    `security_description_line_06_e` varchar(20),
    `security_description_line_06_f` varchar(20),
    `security_description_line_06_g` varchar(20),
    `security_description_line_06_h` varchar(15),
    `record_number_07` varchar(2),
    `security_description_line_07_i` varchar(5),
    `security_description_line_07_j` varchar(20),
    `security_description_line_07_k` varchar(20),
    `group_code_07` varchar(3),
    `trader_code_07` varchar(2),
    `confirm_legend_code_07_a` varchar(2),
    `confirm_legend_code_07_b` varchar(2),
    `registered_rep_exec_rep_rr2_07` varchar(3),
    `second_rr_percent_07` varchar(3),
    `third_rr_code_07` varchar(3),
    `third_rr_percent_07` varchar(3),
    `prospectus_enclosed_07` varchar(1),
    `commission_discount_precent_07` varchar(10),
    `strike_price_07` varchar(9),
    `due_bill_multiplier_07` varchar(5),
    `postage_code_07` varchar(1),
    `commission_concession_code_07_b` varchar(1),
    `commission_preference_code_07` varchar(1),
    `filler_07_d` varchar(2),
    `fund_load_override_07_a` varchar(3),
    `record_number_08` varchar(2),
    `fund_load_override_08_b` varchar(1),
    `quantity_type_08` varchar(1),
    `confirm_line_number_08` varchar(1),
    `exchange_line_number_08` varchar(1),
    `yield_08` varchar(5),
    `yield_type_08` varchar(1),
    `yield_date_08` varchar(7),
    `yield_price_08` varchar(6),
    `trading_away_code_08` varchar(1),
    `filler_08_e` varchar(7),
    `major_clearing_broker_08` varchar(4),
    `major_executing_broker_08` varchar(4),
    `execution_time_08` varchar(4),
    `branch_08_b` varchar(3),
    `irs_no_08` varchar(9),
    `filler_08_f` varchar(3),
    `market_place_08` varchar(5),
    `market_sequence_08` varchar(6),
    `market_override_08` varchar(1),
    `time_in_force_code_08` varchar(1),
    `auto_exec_code_08` varchar(1),
    `issuer_08` varchar(6),
    `issuer_type_08` varchar(2),
    `bond_trader_08` varchar(4),
    `bond_class_code_08` varchar(1),
    `additional_markup_08` varchar(10),
    `terminal_id_08` varchar(4),
    `record_number_09` varchar(2),
    `signon_rep_location_09` varchar(5),
    `registered_rep_signon_rep_09` varchar(3),
    `registered_rep_owning_rep_rr_09` varchar(3),
    `fund_load_percent_09` varchar(4),
    `product_code_09` varchar(12),
    `trading_flat_code_09` varchar(1),
    `_12b1_09` varchar(1),
    `additional_fee_code_09_a` varchar(2),
    `additional_fee_amount_09_a` varchar(9),
    `additional_fee_code_09_b` varchar(2),
    `additional_fee_amount_09_b` varchar(9),
    `additional_fee_code_09_c` varchar(2),
    `additional_fee_amount_09_c` varchar(9),
    `additional_fee_code_09_d` varchar(2),
    `additional_fee_amount_09_d` varchar(9),
    `additional_fee_code_09_e` varchar(2),
    `additional_fee_amount_09_e` varchar(9),
    `additional_fee_code_09_f` varchar(2),
    `additional_fee_amount_09_f` varchar(9),
    `institutional_third_party_09` varchar(4),
    `record_number_10` varchar(2),
    `institutional_misc1_10` varchar(4),
    `institutional_misc2_10` varchar(4),
    `institutional_lot_number_10` varchar(4),
    `bord_tord_code_10` varchar(1),
    `mutual_fund_dtc_number_10` varchar(4),
    `filler_10_g` varchar(1),
    `trade_entry_10` varchar(6),
    `entry_sequence_number_10` varchar(5),
    `solicited_code_10` varchar(1),
    `electronic_trade_id_10` varchar(3),
    `rollup_count_10` varchar(3),
    `confirm_legend_code_10_a` varchar(2),
    `confirm_legend_code_10_b` varchar(2),
    `relationship_id_10` varchar(12),
    `capacity_code_10` varchar(1),
    `confirm_legend_code_10_c` varchar(2),
    `confirm_legend_code_10_d` varchar(2),
    `alternative_investment_code_10` varchar(1),
    `expanded_yield_10` varchar(9),
    `expanded_yield_sign_10` varchar(1),
    `filler_10_h` varchar(31),
    `record_number_11` varchar(2),
    `filler_11_i` varchar(86),
    `revenue_clearing_charge_sign_11` varchar(1),
    `revenue_clearing_charge_amount_11` varchar(7),
    `revenue_miscellaneous_fee_sign_11` varchar(1),
    `revenue_miscellaneous_fee_amount_11_a` varchar(4),
    `record_number_12` varchar(2),
    `revenue_miscellaneous_fee_amount_12_b` varchar(3),
    `product_level_12` varchar(2),
    `concession_code_12` varchar(1),
    `purchase_type_code_12` varchar(2),
    `trade_definition_type_12` varchar(1),
    `trade_definition_trade_id_12` varchar(9),
    `revenue_commission_sign_12` varchar(1),
    `revenue_commission_amount_12` varchar(7),
    `revenue_concession_sign_12` varchar(1),
    `revenue_concession_amount_12` varchar(7),
    `revenue_load_sign_12` varchar(1),
    `revenue_load_amount_12` varchar(7),
    `order_reference_number_12` varchar(11),
    `filler_12_j` varchar(9),
    `input_commission_sign_12` varchar(1),
    `input_commission_amount_12` varchar(10),
    `confirm_legend_code_12_a` varchar(2),
    `confirm_legend_code_12_b` varchar(2),
    `filler_12_k` varchar(2),
    `original_description_12_a` varchar(20),
    `record_number_13` varchar(2),
    `original_description_13_b` varchar(20),
    `execution_time_13` varchar(6),
    `registered_rep_pay_to_rep_13` varchar(3),
    `clearing_charge_sign_13` varchar(1),
    `clearing_charge_13` varchar(7),
    `execution_fee_sign_13` varchar(1),
    `execution_fee_13` varchar(7),
    `foreign_surcharge_sign_13` varchar(1),
    `foreign_surcharge_13` varchar(6),
    `filler_13_l` varchar(7),
    `super_branch_13` varchar(3),
    `filler_13_m` varchar(37),
    PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

