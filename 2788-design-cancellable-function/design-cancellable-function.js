/**
 * @param {Generator} generator
 * @return {[Function, Promise<any>]}
 */
var cancellable = function(generator) {
    let cancel;
    const cancelPromise = new Promise((_, reject) => {
        cancel = () => reject("Cancelled");
    });
    
    cancelPromise.catch(() => {});

    const promise = (async () => {
        let next = generator.next();
        
        while (!next.done) {
            try {
                const result = await Promise.race([next.value, cancelPromise]);
                next = generator.next(result);
            } catch (e) {
                try {
                    next = generator.throw(e);
                } catch (finalError) {
                    throw finalError;
                }
            }
        }
        return next.value;
    })();

    return [cancel, promise];
};
