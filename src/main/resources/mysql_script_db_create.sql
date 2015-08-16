drop database if exists app_ADS;
create database app_ADS;

use app_ADS;

drop table if exists TbUsuarios;
create table TbUsuarios(
    id smallint unsigned not null auto_increment,
    nome varchar(255) not null,
    cpf varchar(11) not null,
    login varchar(10) not null,
    senha varchar(10) not null,        
    ativo bit not null default 1,
    ultimaAtualizacao timestamp not null default current_timestamp on update current_timestamp,
    constraint primary key(id),        
    unique key usuarios_uk_cpf (cpf),
    key usuarios_ik_login (login),    
    key usuarios_ik_ativo (ativo),
    key usuarios_ik_ultimaAtualizacao (ultimaAtualizacao)
) engine=InnoDB;

insert into TbUsuarios values(1, 'admin', '00000000000', 'admin', 'admin', 1, current_timestamp);

drop table if exists TbClientes;
create table TbClientes(
    id mediumint unsigned not null auto_increment,
    razaoSocial varchar(100) not null,
    cpfCnpj varchar(14) not null,
    cep varchar(8) null,
    logradouro varchar(255) null,
    complemento varchar(100) null,
    cidade varchar(30) null,
    estado varchar(20) null,
    fone varchar(11) null,
    celular varchar(11) null,
    email varchar(255) null,
    ativo bit not null default 1,
    ultimaAtualizacao timestamp not null default current_timestamp on update current_timestamp,
    constraint primary key(id),
    unique key clientes_uk_cpfCnpj (cpfCnpj),
    key clientes_ik_ativo (ativo),
    key clientes_ik_ultimaAtualizacao (ultimaAtualizacao)
) engine=InnoDB;

drop table if exists TbFornecedores;
create table TbFornecedores(
    id smallint unsigned not null auto_increment,
    razaoSocial varchar(50) not null,
    cpfCnpj varchar(14) not null,
    ativo bit not null default 1,
    ultimaAtualizacao timestamp not null default current_timestamp on update current_timestamp,
    constraint primary key(id),
    unique key fornecedor_uk_cpfCnpj (cpfCnpj),
    key fornecedores_ik_ativo (ativo),
    key fornecedores_ik_ultimaAtualizacao (ultimaAtualizacao)
) engine=InnoDB;

drop table if exists TbBancos;
create table TbBancos(
    id smallint unsigned not null auto_increment,
    nome varchar(20) not null,
    ativo bit not null default 1,
    ultimaAtualizacao timestamp not null default current_timestamp on update current_timestamp,
    constraint primary key(id),
    unique key bancos_uk_nome (nome),
    key bancos_ik_ativo (ativo),
    key bancos_ik_ultimaAtualizacao (ultimaAtualizacao)
) engine=InnoDB;

drop table if exists TbFormasPagamentos;
create table TbFormasPagamentos (
    id smallint unsigned not null auto_increment,
    nome varchar(20) not null,    
    tipo set('CREDITO', 'DEBITO') null,
    ativo bit not null default 1,
    ultimaAtualizacao timestamp not null default current_timestamp on update current_timestamp,
    constraint primary key(id),
    unique key formaPagamentos_uk_nome (nome),
    key formasPagamentos_ik_tipo (tipo),
    key formasPagamentos_ik_ativo (ativo),
    key formasPagamentos_ik_ultimaAtualizacao (ultimaAtualizacao)
) engine=InnoDB;

drop table if exists TbCentroCustos;
create table TbCentroCustos(
    id smallint unsigned not null auto_increment,
    nome varchar(20) not null,    
    tipo set('CREDITO', 'DEBITO') not null,
    ativo bit not null default 1,
    ultimaAtualizacao timestamp not null default current_timestamp on update current_timestamp,
    constraint primary key(id),
    unique key centroCustos_uk_nome (nome),
    key centroCustos_ik_tipo (tipo),
    key centroCustos_ik_ativo (ativo),
    key centroCustos_ik_ultimaAtualizacao (ultimaAtualizacao)
) engine=InnoDB;

drop table if exists TbCaixas;
create table TbCaixas(
    id mediumint unsigned not null auto_increment,
    data date not null,
    usuarioId smallint unsigned not null,
    situacao enum('ABERTO', 'FECHADO') not null default 'ABERTO',
    ultimaAtualizacao timestamp not null default current_timestamp on update current_timestamp,
    constraint primary key(id),
    constraint caixas_fk_usuario foreign key(usuarioId) 
        references TbUsuarios(id),
    unique key caixas_uk_data (data),
    key caixas_ik_data (data),
    key caixas_ik_usuarioId (usuarioId),
    key caixas_ik_situacao (situacao)
) engine=InnoDB;

drop table if exists TbMovimentos;
create table TbMovimentos(
    id mediumint unsigned not null auto_increment,
    caixaId mediumint unsigned not null,
    centroCustoId smallint unsigned not null,
    valor decimal(7, 2) not null,
    usuarioId smallint unsigned not null,
    situacao enum('ABERTO', 'PAGO', 'CANCELADO') not null default 'ABERTO',
    ultimaAtualizacao timestamp not null default current_timestamp on update current_timestamp,
    constraint primary key(id),
    constraint movimentos_fk_caixa foreign key(caixaId) 
        references TbCaixas(id),
    constraint movimentos_fk_centroCusto foreign key(centroCustoId) 
        references TbCentroCustos(id),
    constraint movimentos_fk_usuario foreign key(usuarioId) 
        references TbUsuarios(id),
    key movimentos_ik_caixa (caixaId),
    key movimentos_ik_centroCusto (centroCustoId),
    key movimentos_ik_usuarioId (usuarioId),
    key movimentos_ik_situacao (situacao)
) engine=InnoDB;

drop table if exists TbPagamentos;
create table TbPagamentos (
    id mediumint unsigned not null auto_increment,
    movimentoId mediumint unsigned null,
    formaPagamentoId smallint unsigned not null,
    numDocumento varchar(20) null,
    bancoId smallint unsigned null,
    valor decimal(7, 2) not null,
    usuarioId smallint unsigned not null,
    ultimaAtualizacao timestamp not null default current_timestamp on update current_timestamp,
    constraint primary key(id),
    constraint pagamentos_fk_movimento foreign key(movimentoId) 
        references TbMovimentos(id),
    constraint pagamentos_fk_formaPagamento foreign key(formaPagamentoId) 
        references TbFormasPagamentos(id),
    constraint pagamentos_fk_banco foreign key(bancoId) 
        references TbBancos(id),
    constraint pagamentos_fk_usuario foreign key(usuarioId) 
        references TbUsuarios(id),
    unique key pagamentos_uk_movimentoFormaPag (movimentoId, formaPagamentoId),
    key pagamentos_ik_movimentoId (movimentoId),
    key pagamentos_ik_formaPagamento (formaPagamentoId),
    key pagamentos_ik_usuarioId (usuarioId),
    key pagamentos_ik_banco (bancoId)
) engine=InnoDB;

drop table if exists TbReceitas;
create table TbReceitas(
    id mediumint unsigned not null auto_increment,
    data date not null,
    clienteId mediumint unsigned not null,
    centroCustoId smallint unsigned not null,
    movimentoId mediumint unsigned null,
    valor decimal(7, 2) not null,
    situacao enum('ABERTO', 'PAGO', 'CANCELADO') not null default 'ABERTO',
    ultimaAtualizacao timestamp not null default current_timestamp on update current_timestamp,
    constraint primary key(id),
    constraint receitas_fk_cliente foreign key(clienteId) 
        references TbClientes(id),
    constraint receitas_fk_centroCusto foreign key(centroCustoId) 
        references TbCentroCustos(id),
    constraint receitas_fk_movimento foreign key(movimentoId) 
        references TbMovimentos(id),
    key receitas_ik_cliente (clienteId),
    key receitas_ik_centroCusto (centroCustoId),
    key receitas_ik_movimento (movimentoId),
    key receitas_ik_situacao (situacao),
    key receitas_ik_ultimaAtualizacao (ultimaAtualizacao)
) engine=InnoDB;

drop table if exists TbDespesas;
create table TbDespesas(
    id mediumint unsigned not null auto_increment,
    data date not null,
    fornecedorId smallint unsigned not null,
    centroCustoId smallint unsigned not null,
    movimentoId mediumint unsigned null,
    valor decimal(7, 2) not null,
    situacao enum('ABERTO', 'PAGO', 'CANCELADO') not null default 'ABERTO',
    ultimaAtualizacao timestamp not null default current_timestamp on update current_timestamp,
    constraint primary key(id),
    constraint despesas_fk_fornecedor foreign key(fornecedorId) 
        references TbFornecedores(id),
    constraint despesas_fk_centroCusto foreign key(centroCustoId) 
        references TbCentroCustos(id),
    constraint despesas_fk_movimento foreign key(movimentoId) 
        references TbMovimentos(id),
    key despesas_ik_fornecedor (fornecedorId),
    key despesas_ik_centroCusto (centroCustoId),
    key despesas_ik_movimento (movimentoId),
    key despesas_ik_situacao (situacao),
    key despesas_ik_ultimaAtualizacao (ultimaAtualizacao)
) engine=InnoDB;

drop table if exists TbAuditorias;
create table TbAuditorias(
    id int unsigned not null auto_increment,
    usuarioId smallint unsigned not null,
    data timestamp not null default current_timestamp,
    operacao enum('INSERT', 'UPDATE', 'DELETE', 'LOAD') not null,    
    constraint primary key(id),
    constraint auditorias_fk_usuario foreign key(usuarioId) 
        references TbUsuarios(id),
    key auditorias_ik_operacao (operacao),    
    key auditorias_ik_usuario (usuarioId),    
    key auditorias_ik_data (data)
) engine=InnoDB;

drop table if exists TbInstantes;
create table TbInstantes(
    id int unsigned not null auto_increment,
    instante enum('BEFORE', 'AFTER') not null,
    auditoriaId int unsigned not null,
    constraint primary key(id),
    constraint audinst_fk_auditoria foreign key(auditoriaId) 
        references TbAuditorias(id),
    key instante_ik_instante (instante),
    key instante_ik_auditoria (auditoriaId)
) engine=InnoDB;

drop table if exists TbAudUsuarios;
create table TbAudUsuarios
    select * from TbUsuarios limit 0;
alter table TbAudUsuarios engine=InnoDB,
    add column instanteId int unsigned not null,
    add constraint primary key(instanteId),
    add constraint audusuarios_fk_auditoria foreign key(instanteId) 
        references TbInstantes(id),
    add key audusuarios_ik_instante (instanteId),
    add key audusuarios_ik_id (id);

drop table if exists TbAudClientes;
create table TbAudClientes
    select * from TbClientes limit 0;
alter table TbAudClientes engine=InnoDB,
    add column instanteId int unsigned not null,
    add constraint primary key(instanteId),
    add constraint audclientes_fk_auditoria foreign key(instanteId) 
        references TbInstantes(id),
    add key audclientes_ik_instante (instanteId),
    add key audclientes_ik_id (id);

drop table if exists TbAudFornecedores;
create table TbAudFornecedores
    select * from TbFornecedores limit 0;
alter table TbAudFornecedores engine=InnoDB,
    add column instanteId int unsigned not null,
    add constraint primary key(instanteId),
    add constraint audfornecedores_fk_auditoria foreign key(instanteId) 
        references TbInstantes(id),
    add key audfornecedores_ik_instante (instanteId),
    add key audfornecedores_ik_id (id);

drop table if exists TbAudBancos;
create table TbAudBancos
    select * from TbBancos limit 0;
alter table TbAudBancos engine=InnoDB,
    add column instanteId int unsigned not null,
    add constraint primary key(instanteId),
    add constraint audbancos_fk_auditoria foreign key(instanteId) 
        references TbInstantes(id),
    add key audbancos_ik_instante (instanteId),
    add key audbancos_ik_id (id);

drop table if exists TbAudFormasPagamentos;
create table TbAudFormasPagamentos
    select * from TbFormasPagamentos limit 0;
alter table TbAudFormasPagamentos engine=InnoDB,
    add column instanteId int unsigned not null,
    add constraint primary key(instanteId),
    add constraint audformaspagamentos_fk_auditoria foreign key(instanteId) 
        references TbInstantes(id),
    add key audformaspagamentos_ik_instante (instanteId),
    add key audformaspagamentos_ik_id (id);

drop table if exists TbAudCentroCustos;
create table TbAudCentroCustos
    select * from TbCentroCustos limit 0;
alter table TbAudCentroCustos engine=InnoDB,
    add column instanteId int unsigned not null,
    add constraint primary key(instanteId),
    add constraint audcentrocustos_fk_auditoria foreign key(instanteId) 
        references TbInstantes(id),
    add key audcentrocustos_ik_instante (instanteId),
    add key audcentrocustos_ik_id (id);

drop table if exists TbAudCaixas;
create table TbAudCaixas
    select * from TbCaixas limit 0;
alter table TbAudCaixas engine=InnoDB,
    add column instanteId int unsigned not null,
    add constraint primary key(instanteId),
    add constraint audcaixas_fk_auditoria foreign key(instanteId) 
        references TbInstantes(id),
    add key audcaixas_ik_instante (instanteId),
    add key audcaixas_ik_id (id);

drop table if exists TbAudMovimentos;
create table TbAudMovimentos
    select * from TbMovimentos limit 0;
alter table TbAudMovimentos engine=InnoDB,
    add column instanteId int unsigned not null,
    add constraint primary key(instanteId),
    add constraint audmovimentos_fk_auditoria foreign key(instanteId) 
        references TbInstantes(id),
    add key audmovimentos_ik_instante (instanteId),
    add key audmovimentos_ik_id (id);

drop table if exists TbAudPagamentos;
create table TbAudPagamentos
    select * from TbPagamentos limit 0;
alter table TbAudPagamentos engine=InnoDB,
    add column instanteId int unsigned not null,
    add constraint primary key(instanteId),
    add constraint audpagamentos_fk_auditoria foreign key(instanteId) 
        references TbInstantes(id),
    add key audpagamentos_ik_instante (instanteId),
    add key audpagamentos_ik_id (id);

drop table if exists TbAudReceitas;
create table TbAudReceitas
    select * from TbReceitas limit 0;
alter table TbAudReceitas engine=InnoDB,
    add column instanteId int unsigned not null,
    add constraint primary key(instanteId),
    add constraint audreceitas_fk_auditoria foreign key(instanteId) 
        references TbInstantes(id),
    add key audreceitas_ik_instante (instanteId),
    add key audreceitas_ik_id (id);

drop table if exists TbAudDespesas;
create table TbAudDespesas
    select * from TbDespesas limit 0;
alter table TbAudDespesas engine=InnoDB,
    add column instanteId int unsigned not null,
    add constraint primary key(instanteId),
    add constraint auddespesas_fk_auditoria foreign key(instanteId) 
        references TbInstantes(id),
    add key auddespesas_ik_instante (instanteId),
    add key auddespesas_ik_id (id);
