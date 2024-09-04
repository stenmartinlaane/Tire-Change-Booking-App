

export class TimeHelper {

    static getTodaysDate() {
        const today = new Date();
        // Format the date as YYYY-MM-DD
        const formattedDate = today.toISOString().split('T')[0];
        return formattedDate;
    }

    static getFutureDate(days: number): string {
        const today = new Date();
        today.setDate(today.getDate() + days);
        return today.toISOString().split('T')[0]; // Format as YYYY-MM-DD
    }
}

