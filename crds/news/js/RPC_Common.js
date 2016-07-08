
// Provide a default path to dwr.engine
if (dwr == null) var dwr = {};
if (dwr.engine == null) dwr.engine = {};
if (DWREngine == null) var DWREngine = dwr.engine;

if (RPC_Common == null) var RPC_Common = {};
RPC_Common._path = '/website/dwr';
RPC_Common.getAllConfigMenuTreeNode = function(callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'getAllConfigMenuTreeNode', callback);
}
RPC_Common.getAllTemplateTreeNode = function(p0, callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'getAllTemplateTreeNode', p0, callback);
}
RPC_Common.getAllConfigFooterTreeNode = function(callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'getAllConfigFooterTreeNode', callback);
}
RPC_Common.listAllMenus = function(callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'listAllMenus', callback);
}
RPC_Common.listNowUsingFooter = function(callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'listNowUsingFooter', callback);
}
RPC_Common.findConfigMenu = function(p0, callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'findConfigMenu', p0, callback);
}
RPC_Common.findConfigMenu4Update = function(p0, callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'findConfigMenu4Update', p0, callback);
}
RPC_Common.getConfigMenu4Create = function(p0, p1, callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'getConfigMenu4Create', p0, p1, callback);
}
RPC_Common.updateConfigMenu = function(p0, callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'updateConfigMenu', p0, callback);
}
RPC_Common.checkUrl = function(p0, callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'checkUrl', p0, callback);
}
RPC_Common.removeConfigMenu = function(p0, callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'removeConfigMenu', p0, callback);
}
RPC_Common.getNavigationByMenuId = function(p0, callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'getNavigationByMenuId', p0, callback);
}
RPC_Common.listChildMenus = function(p0, callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'listChildMenus', p0, callback);
}
RPC_Common.saveWidges = function(p0, p1, callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'saveWidges', p0, p1, callback);
}
RPC_Common.getCalendar = function(p0, p1, callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'getCalendar', p0, p1, callback);
}
RPC_Common.getJyFair = function(p0, callback) {
  dwr.engine._execute(RPC_Common._path, 'RPC_Common', 'getJyFair', p0, callback);
}
