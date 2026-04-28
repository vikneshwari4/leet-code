/**
 * @param {Function} fn
 * @return {Function}
 */
function memoize(fn) {
    const root = new Map();
    const weakRoot = new WeakMap();
    const RESULT = Symbol('result');

    return function(...args) {
        let current = root;
        let weakCurrent = weakRoot;

        for (const arg of args) {
            const isObject = arg !== null && (typeof arg === 'object' || typeof arg === 'function');
            const provider = isObject ? weakCurrent : current;
            
            if (!provider.has(arg)) {
                provider.set(arg, [new Map(), new WeakMap()]);
            }
            
            [current, weakCurrent] = provider.get(arg);
        }

        if (!current.has(RESULT)) {
            current.set(RESULT, fn(...args));
        }
        
        return current.get(RESULT);
    }
}
