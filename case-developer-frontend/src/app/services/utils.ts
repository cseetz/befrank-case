export default class Utils {
  static formatterNumberToEuro(waarde: number) {
    return new Intl.NumberFormat('nl-NL', {
      style: 'currency',
      currency: 'EUR'
    }).format(waarde);
  }
}
